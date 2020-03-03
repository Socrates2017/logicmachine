
/**
 * 缩小右边栏
 */
function indentRight() {
    var classes = document.getElementById("left").classList;
    var leftNum = 3;

    for (var j = 0, len = classes.length; j < len; j++) {
        var className = classes[j];
        if (className.substr(0, 7) == "col-sm-") {
            leftNum = parseInt(className.substr(7))
            break;
        }
    }

    var numRight = 12 - leftNum;
    var leftClassOld = 'col-sm-' + leftNum + ' col-md-' + leftNum + ' col-lg-' + leftNum;
    var rightClassOld = 'col-sm-' + numRight + ' col-md-' + numRight + ' col-lg-' + numRight;

    var newNum = leftNum + 1;
    var newNumRight = 12 - newNum;
    var leftClassNew = 'col-sm-' + newNum + ' col-md-' + newNum + ' col-lg-' + newNum;
    var rightClassNew = 'col-sm-' + newNumRight + ' col-md-' + newNumRight + ' col-lg-' + newNumRight;

    $("#left").removeClass(leftClassOld);
    $("#left").addClass(leftClassNew)
    $("#right").removeClass(rightClassOld);
    $("#right").addClass(rightClassNew)
    changeCanvas()

}

/**
 * 缩小左边栏
 */
function indentLeft() {
    var classes = document.getElementById("left").classList;
    var leftNum = 3;
    for (var j = 0, len = classes.length; j < len; j++) {
        var className = classes[j];
        if (className.substr(0, 7) == "col-sm-") {
            leftNum = parseInt(className.substr(7))
            break;
        }
    }

    var numRight = 12 - leftNum;
    var leftClassOld = 'col-sm-' + leftNum + ' col-md-' + leftNum + ' col-lg-' + leftNum;
    var rightClassOld = 'col-sm-' + numRight + ' col-md-' + numRight + ' col-lg-' + numRight;

    var newNum = leftNum - 1;
    var newNumRight = 12 - newNum;
    var leftClassNew = 'col-sm-' + newNum + ' col-md-' + newNum + ' col-lg-' + newNum;
    var rightClassNew = 'col-sm-' + newNumRight + ' col-md-' + newNumRight + ' col-lg-' + newNumRight;

    $("#left").removeClass(leftClassOld);
    $("#left").addClass(leftClassNew)
    $("#right").removeClass(rightClassOld);
    $("#right").addClass(rightClassNew)
    changeCanvas()

}

/**
 * 隐藏左边栏
 */
function hideLeft() {
    var classes = document.getElementById("left").classList;
    var leftNum = 3;
    for (var j = 0, len = classes.length; j < len; j++) {
        var className = classes[j];
        if (className.substr(0, 7) == "col-sm-") {
            leftNum = parseInt(className.substr(7))
            break;
        }
    }
    var numRight = 12 - leftNum;
    var rightClassOld = 'col-sm-' + numRight + ' col-md-' + numRight + ' col-lg-' + numRight;

    $("#left").hide()
    $("#right").removeClass(rightClassOld);
    $("#right").addClass('col-sm-12 col-md-12 col-lg-12')

    $("#indentRight").hide()
    changeCanvas()
}


/**
 * 显示左边栏
 */
function showLeft() {
    var classes = document.getElementById("left").classList;
    var leftNumOld = 3;
    for (var j = 0, len = classes.length; j < len; j++) {
        var className = classes[j];
        if (className.substr(0, 7) == "col-sm-") {
            leftNumOld = parseInt(className.substr(7))
            break;
        }
    }

    var newNumRight = 12 - leftNumOld;
    var rightClassNew = 'col-sm-' + newNumRight + ' col-md-' + newNumRight + ' col-lg-' + newNumRight;

    $("#left").show()
    $("#right").removeClass('col-sm-12 col-md-12 col-lg-12');
    $("#right").addClass(rightClassNew)

    $("#indentRight").show()

}

/**
 * 控制隐藏或显示左边栏
 */
function hideOrShowLeft() {
    var ishide = $("#indentRight").is(':hidden');
    if (ishide) {
        showLeft()
    } else {
        hideLeft()
    }
}

/**
 * 获取章节目录并局部刷新
 *
 * @param id
 */
function getRootFactList() {
    $.ajax({
        url: "/fact/rootFactList",
        type: "GET",
        datatype: "json",
        async: true,
        success: function (result) {
            var status = result.status;

            if (status == 1) {
                var data = result.data;
                var html = '<ul class="" >';
                for (var i in data) {
                    var fact = data[i];
                    html += '<li id="li-' + fact.factId + '" class=""  style="margin-top:20px;" >';
                    html += '&emsp;<a href="javascript:void(0);" onclick="getFactTree(' + fact.factId + ')"><span id="name-' + fact.factId + '">' + fact.name + '</span></a>';

                    html += '<div id="index_' + fact.factId + '" style="padding-left:20px;" ></div>';
                }
                html += '</ul>'
                $("#root-fact-list").html(html);

            } else {
                console.log(result.message)
            }
        }
    });
}

function getFactTree(factId) {
    $.ajax({
        url: "/fact/tree/"+factId,
        type: "GET",
        datatype: "json",
        async: true,
        success: function (result) {
            var status = result.status;

            if (status == 1) {
                var data = result.data;


                var html = '<ul class="" >';



            } else {
                console.log(result.message)
            }
        }
    });

}






$(function () {
    $("#left-scroll").height(document.documentElement.clientHeight - 30);
    getRootFactList()



});
