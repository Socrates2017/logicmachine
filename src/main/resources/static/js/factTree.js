var factId = 3;
var nodes = [];
var edges = [];
var nodeIndex = 0;

function getFactTree() {
    $.ajax({
        url: "/fact/tree/" + factId,
        type: "GET",
        datatype: "json",
        async: true,
        success: function (result) {
            var status = result.status;
            if (status == 1) {
                var fact = result.data;
                nodeIndex = 0;

                proceesData(null, fact, 0, 0);

                let options = {
                    name: 'breadthfirst',
                    fit: true, // whether to fit the viewport to the graph
                    directed: true, // whether the tree is directed downwards (or edges can point in any direction if false)
                    padding: 15, // padding on fit
                    circle: false, // put depths in concentric circles if true, put depths top down if false
                    grid: true, // whether to create an even grid into which the DAG is placed (circle:false only)
                    spacingFactor: 1.05, // positive spacing factor, larger => more space between nodes (N.B. n/a if causes overlap)
                    boundingBox: undefined, // constrain layout bounds; { x1, y1, x2, y2 } or { x1, y1, w, h }
                    avoidOverlap: true, // prevents node overlap, may overflow boundingBox if not enough space
                    nodeDimensionsIncludeLabels: false, // Excludes the label when calculating node bounding boxes for the layout algorithm
                    roots: undefined, // the roots of the trees
                    maximal: false, // whether to shift nodes down their natural BFS depths in order to avoid upwards edges (DAGS only)
                    animate: false, // whether to transition the node positions
                    animationDuration: 500, // duration of animation in ms if enabled
                    animationEasing: undefined, // easing of animation if enabled,
                    animateFilter: function ( node, i ){ return true; }, // a function that determines whether the node should be animated.  All nodes animated by default on animate enabled.  Non-animated nodes are positioned immediately when the layout starts
                    ready: undefined, // callback on layoutready
                    stop: undefined, // callback on layoutstop
                    transform: function (node, position ){ return position; } // transform a given node position. Useful for changing flow direction in discrete layouts
                };

                cytoscape({
                    container: document.getElementById('cy'),
                    style: [

                        {
                            selector: 'node[factId]',
                            style: {
                                'background-color': '#555',
                                'color': "#fff",
                                'content': 'data(factId)',
                                'font-size': "12px",
                                'text-halign': "center",
                                'text-outline-color': "#555",
                                'text-outline-width': "2px",
                                'text-valign': "center"

                            }
                        },
                        {
                            selector: 'node[atomicId>0]',
                            css: {'background-color': '#6FB1FC', 'content': 'data(atomicId)'}
                        },
                        {
                            selector: 'edge',
                            css: {'content': 'data(relationship)', 'target-arrow-shape': 'triangle'}
                        }
                    ],
                    elements: {
                        nodes: nodes,
                        edges: edges
                    },
                    layout: options
                });


            } else {
                console.log(result.message)
            }
        }
    });
}


function proceesData(parentNode, fact, level, index) {
    nodeIndex++;
    let node = {};
    let nodeData = {};
    if (parentNode != null) {
        nodeData.id = parentNode.id + "#" + index;
    } else {
        nodeData.id = index;
    }
    nodeData.name = fact.name;
    nodeData.factId = fact.factId;
    nodeData.atomicId = fact.atomicId;
    node.data = nodeData;
    nodes.push(node);

    let children = fact.children;
    if (children != null) {
        for (let i = 0; i < children.length; i++) {
            let childFact = children[i];

            let edge = {};
            let edgeData = {};
            edgeData.source = nodeData.id;
            edgeData.relationship = fact.connective;

            let nextLevel = level + 1;
            edgeData.target = nodeData.id + "#" + i;
            edge.data = edgeData;
            edges.push(edge);

            proceesData(nodeData, childFact, nextLevel, i)
        }
    }
}

/**
 *
 *
 * 下面设置布局 给我们提供了几种布局。

 preset 该preset布局使节点在您手动指定的位置。
 grid grid布局使节点在良好隔开的网格。
 circle 该circle布局使节点在一个圆圈。
 concentric 该concentric布局定位在同心圆节点，根据您所指定的节点分隔成水平的度量。此布局设置concentric值ele.scratch()。
 breadthfirst breadthfirst布局使在层次结构中的节点，基于所述图的breadthfirst遍历。默认的自上而下模式
 random, //该random布局使节点在视口内随机位置。
 *
 */


$(function () {
    // changeCanvas()
    var uri = window.location.pathname;
    factId = uri.substr(10);

    getFactTree();

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
    var graph = document.getElementById('cy');
    graph.style.width = clientWidth;
    graph.style.height = clientHeight;
}