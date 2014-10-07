/**
 * Created by tiger on 14-9-5.
 */
var settings = require('../settings'),
    mongoose = require('mongoose'),
    markdown = require('markdown').markdown;

//mongoose.connect(settings.url);

var postSchema = new mongoose.Schema({
    name: String,
    head: String,
    title: String,
    tags: [],
    post: String,
    time: {},
    comments: [],
    reprint_info: {},
    pv: Number
}, {
    collection: 'posts'
});

var postModel = mongoose.model('Post', postSchema);

function Post(name, head, title, tags, post) {
    this.name = name;
    this.head = head;
    this.title = title;
    this.tags = tags;
    this.post = post;
}

//存储一篇文章及其相关信息
Post.prototype.save = function(callback) {
    var date = new Date();
    //存储各种时间格式，方便以后扩展
    var time = {
        date: date,
        year: date.getFullYear(),
        month: date.getFullYear() + "-" + (date.getMonth() + 1),
        day: date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate(),
        minute: date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate() + " " + date.getHours() + ":" +
            (date.getMinutes() < 10 ? '0' + date.getMinutes() : date.getMinutes())
    };

    //要存入数据库的文档
    var post = {
        name: this.name,
        head: this.head,
        time: time,
        title:this.title,
        tags: this.tags,
        post: this.post,
        comments: [],
        reprint_info: {},
        pv: 0
    };

    var newPost = new postModel(post);

    newPost.save(function (err) {
        if (err) {
            return callback(err);
        }
        callback(null);
    })
};

//一次获取十篇文章
Post.getAll = function(name, page, callback){
    var query = {};
    if(name){
        query.name = name;
    }
    postModel.count(function(err, total){
        //根据 query 对象查询，并跳过前 (page-1)*10 个结果，返回之后的 10 个结果
        postModel.find(query).skip((page - 1) * 10).limit(10).sort({time: -1}).find(function(err, docs){
            if (err) {
                return callback(err);
            }

            //解析 markdown 为 html
            docs.forEach(function (doc) {
                doc.post = markdown.toHTML(doc.post);
            });
            callback(null, docs, total);
        });
    });
};

//获取一篇文章
Post.getOne = function(name, day, title, callback) {
    postModel.findOne({name: name, "time.day": day, title: title}, function(err, doc){
        if (err) {
            return callback(err);
        }
        if (doc) {
            //每访问 1 次，pv 值增加 1
            postModel.update({name: name, "time.day": day, title: title}, {$inc: {pv: 1}}, function(err){
                if (err) {
                    return callback(err);
                }
            });
            //解析 markdown 为 html
            doc.post = markdown.toHTML(doc.post);
            doc.comments.forEach(function (comment) {
                comment.content = markdown.toHTML(comment.content);
            });
            callback(null, doc);//返回查询的一篇文章
        }
    });
};

//返回原始发表的内容（markdown 格式）
Post.edit = function(name, day, title, callback) {
    postModel.findOne({name: name, "time.day": day, title: title}, function(err, doc){
        if (err) {
            return callback(err);
        }
        callback(null, doc);//返回查询的一篇文章（markdown 格式
    });
};

//更新一篇文章及其相关信息
Post.update = function(name, day, title, post, callback) {
    postModel.update({name: name, "time.day": day, title: title}, {$set: {post: post}}, function(err){
        if (err) {
            return callback(err);
        }
        callback(null);
    });
};

//删除一篇文章
Post.remove = function(name, day, title, callback) {
    postModel.findOne({name: name, "time.day": day, title: title}, function(err, doc){
        if (err) {
            return callback(err);
        }
        //如果有 reprint_from，即该文章是转载来的，先保存下来 reprint_from
        var reprint_from = "";
        if (doc.reprint_info.reprint_from) {
            reprint_from = doc.reprint_info.reprint_from;
        }
        if (reprint_from != "") {
            //更新原文章所在文档的 reprint_to
            postModel.update({name: name, "time.day": day, title: title}, {
                $pull: {
                    "reprint_info.reprint_to": {
                        "name": name,
                        "day": day,
                        "title": title
                    }
                }}, function(err){
                    if (err) {
                        return callback(err);
                    }
                }
            )
        }
        //删除转载来的文章所在的文档
        postModel.remove({name: name, "time.day": day, title: title}, function(err){
            if (err) {
                return callback(err);
            }
        })
    });
};

//返回所有文章存档信息
Post.getArchive = function(callback) {
    //返回只包含 name、time、title 属性的文档组成的存档数组
    postModel.find({}, {name: 1, time: 1, title: 1}).sort({time: -1}).find(function(err, docs){
        if (err) {
            return callback(err);
        }
        callback(null, docs);
    });
};

//返回所有标签
Post.getTags = function(callback) {
    postModel.distinct("tags", function(err, docs){
        if (err) {
            return callback(err);
        }
        callback(null, docs);
    });
};

//返回含有特定标签的所有文章
Post.getTag = function(tag, callback) {
    //查询所有 tags 数组内包含 tag 的文档
    //并返回只含有 name、time、title 组成的数组
    postModel.find({tags: tag}, {name: 1, time: 1, title: 1}).sort({time: -1}).find(function(err, docs){
        if (err) {
            return callback(err);
        }
        callback(null, docs);
    });
};

//返回通过标题关键字查询的所有文章信息
Post.search = function(keyword, callback) {
    var pattern = new RegExp("^.*" + keyword + ".*$", "i");
    postModel.find({title: pattern}, {name: 1, time: 1, title: 1}).sort({time: -1}).find(function(err, docs){
        if (err) {
            return callback(err);
        }
        callback(null, docs);
    });
};

//转载一篇文章
Post.reprint = function(reprint_from, reprint_to, callback) {
    //找到被转载的文章的原文档
    postModel.findOne({name: reprint_from.name, "time.day": reprint_from.day, title: reprint_from.title}, function(err, doc){
        if (err) {
            return callback(err);
        }

        var date = new Date();
        var time = {
            date: date,
            year : date.getFullYear(),
            month : date.getFullYear() + "-" + (date.getMonth() + 1),
            day : date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate(),
            minute : date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate() + " " +
                date.getHours() + ":" + (date.getMinutes() < 10 ? '0' + date.getMinutes() : date.getMinutes())
        };

        delete doc._id;//注意要删掉原来的 _id

        doc.name = reprint_to.name;
        doc.head = reprint_to.head;
        doc.time = time;
        doc.title = (doc.title.search(/[转载]/) > -1) ? doc.title : "[转载]" + doc.title;
        doc.comments = [];
        doc.reprint_info = {"reprint_from": reprint_from};
        doc.pv = 0;

        //更新被转载的原文档的 reprint_info 内的 reprint_to
        postModel.update({name: reprint_from.name, "time.day": reprint_from.day, title: reprint_from.title}, {
            $push: {
                "reprint_info.reprint_to": {
                    "name": doc.name,
                    "day": time.day,
                    "title": doc.title
                }
            }
        }, function(err) {
            if (err) {
                return callback(err);
            }
        });

        //将转载生成的副本修改后存入数据库，并返回存储后的文档
        var newPost = new postModel(doc);

        newPost.save(function (err, post) {
            if (err) {
                return callback(err);
            }
            callback(null, post[0]);
        })
    });
};

module.exports = Post;