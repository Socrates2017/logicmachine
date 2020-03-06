var factId = 3;

function getFactTree() {
    $.ajax({
        url: "/fact/tree/" + factId,
        type: "GET",
        datatype: "json",
        async: true,
        success: function (result) {
            var status = result.status;
            if (status == 1) {
                var data = result.data;

                var canvas = document.getElementById('treeCanvas');
                var ctx = canvas.getContext("2d");
                ctx.font = "15px Arial";
                ctx.textBaseline = "top";
                draw(ctx, data, 0);


            } else {
                console.log(result.message)
            }
        }
    });
}

var rootX = 50;
var rootY = 50;
var padding = 10;
var nodePaddingX = 100;
var modePaddingY = 50;
var leafnum = 0;

function draw(ctx, fact, level) {

    console.log("factId:" + fact.factId + ">>level:" + level)
    var name = fact.name + ":" + fact.factId;

    if (name != null) {

        fillText(ctx, rootX + nodePaddingX * level, rootY + modePaddingY * leafnum, padding, name)
    } else {
        fillText(ctx, rootX + nodePaddingX * level, rootY + modePaddingY * leafnum, padding, "空")
    }

    let children = fact.children;
    if (children != null) {
        for (let i = 0; i < children.length; i++) {
            let childFact = children[i];
            let nextLevel = level + 1;
            draw(ctx, childFact, nextLevel)
            if (childFact.children == null) {
                leafnum++;
            }
        }
    }
}

/**
 * 绘制一个带边框的文本
 * @param ctx 画板
 * @param x X轴的中点
 * @param y y轴中点
 * @param padding 边距
 * @param name 文本
 */
function fillText(ctx, x, y, padding, text) {

    let textWidth = ctx.measureText(text).width;
    let textHeight = ctx.measureText("口").width;

    let rectWidth = textWidth + padding * 2;
    let rectHeight = textHeight + padding * 2;

    let rectLeft = x - rectWidth / 2;
    let rectTop = y - rectHeight / 2;
    ctx.strokeRect(rectLeft, rectTop, rectWidth, rectHeight);

    let textLeft = x - textWidth / 2;
    let textTop = y - textHeight / 2;
    ctx.fillText(text, textLeft, textTop);
}


$(function () {
    changeCanvas();

    var uri = window.location.pathname;
    factId = uri.substr(10);
    getFactTree()
});

window.onresize = function () {
    //changeCanvas()
}

/**
 * 画板大小自适应
 */
function changeCanvas() {
    var clientHeight = document.documentElement.clientHeight;
    var clientWidth = document.documentElement.clientWidth;
    var treeCanvas = document.getElementById('treeCanvas');
    treeCanvas.width = clientWidth;
    treeCanvas.height = clientHeight;
}