<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="icon" href="ico/ball.ico">
        <title>demo-dubbo-consumer</title>
        <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="bootstrap/css/bootstrap-theme.min.css">
        <link rel="stylesheet" href="bootstrap/css/theme.css">
        <link rel="stylesheet" href="sweetalert/css/sweetalert.css">
        <script src="jquery/jquery-1.11.3.min.js"></script>
        <script src="bootstrap/js/bootstrap.min.js"></script>
        <script src="sweetalert/js/sweetalert.min.js"></script>

        <script>
            jQuery(function($) {
                $("#submitBtn").click(function() {
                    swal("Oops...", "Something went wrong!", "error");
                });

                $("#inputBtn").click(function() {
                    swal({
                                title: "An input!",
                                text: "Write something interesting:",
                                type: "input",
                                showCancelButton: true,
                                closeOnConfirm: false,
                                animation: "slide-from-top",
                                inputPlaceholder: "Write something"
                            },
                            function (inputValue) {
                                if (inputValue === false) return false;
                                if (inputValue === "") {
                                    swal.showInputError("You need to write something!");
                                    return false
                                }
                                $.ajax({
                                    url: "say",
                                    method: "GET",
                                    data: {name: inputValue},
                                    success: function(response) {
                                        swal("Nice!", response, "success");
                                    }
                                })
                            });
                });
            })
        </script>
    </head>
    <body role="document">
        <nav class="navbar navbar-inverse navbar-fixed-top">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target=".navbar-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="#">Project name</a>
                </div>
                <div class="navbar-collapse collapse">
                    <ul class="nav navbar-nav">
                        <li class="active"><a href="#">Home</a></li>
                    </ul>
                </div>
            </div>
        </nav>

        <div class="container theme-showcase" role="main">
            <div class="jumbotron">
                <h1>Dubbo Consumer Test</h1>
                <p>Test...</p>
            </div>
            <div>
                <button class="btn btn-lg btn-info" id="submitBtn" type="button">Submit</button>
                <button class="btn btn-lg btn-success" id="inputBtn" type="button">Input</button>
            </div>
        </div>

    </body>
</html>
