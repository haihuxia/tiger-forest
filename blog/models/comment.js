var settings = require('../settings'),
    mongoose = require('mongoose');

//mongoose.connect(settings.url);

//var postSchema = new mongoose.Schema({
//    name: String,
//    head: String,
//    title: String,
//    tags: String,
//    post: String,
//    time: {},
//    comments: [],
//    reprint_info: {},
//    pv: Number
//}, {
//    collection: 'posts'
//});

//var postModel = mongoose.model('Post', postSchema);

//function Post(name, head, title, tags, post) {
//    this.name = name;
//    this.head = head;
//    this.title = title;
//    this.tags = tags;
//    this.post = post;
//}

function Comment(name, day, title, comment) {
    this.name = name;
    this.day = day;
    this.title = title;
    this.comment = comment;
}

//存储一条留言信息
Comment.prototype.save = function(callback) {
    var name = this.name,
        day = this.day,
        title = this.title,
        comment = this.comment;

    //通过用户名、时间及标题查找文档，并把一条留言对象添加到该文档的 comments 数组里
    postModel.update({name: name, "time.day": day, title: title}, {$push: {comments: comment}}, function(err){
        if (err) {
            return callback(err);
        }
        callback(null);
    });
};

module.exports = Comment;