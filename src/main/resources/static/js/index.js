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
        url: "/enum/atomicfactFunList",
        type: "GET",
        datatype: "json",
        async: true,
        success: function (result) {
            var status = result.code;

            if (status == 1) {
                var data = result.data;
                var atomicFactFunctioList =data['atomicFactFunctioList'];
                var atomicfactFunSelect = document.getElementById("atomic_fact_function");
                for (var i in atomicFactFunctioList) {
                    var atomicfactFunName = atomicFactFunctioList[i];
                    atomicfactFunSelect.options.add(new Option(atomicfactFunName, atomicfactFunName))
                }

                var operatorList =data['operatorList'];
                var operatorSelect = document.getElementById("operator");
                for (var i in operatorList) {
                    var operator = operatorList[i];
                    operatorSelect.options.add(new Option(operator, operator))
                }


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
