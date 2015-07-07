var labelType, useGradients, nativeTextSupport, animate;
var st; 

(function() {
  var ua = navigator.userAgent,
      iStuff = ua.match(/iPhone/i) || ua.match(/iPad/i),
      typeOfCanvas = typeof HTMLCanvasElement,
      nativeCanvasSupport = (typeOfCanvas == 'object' || typeOfCanvas == 'function'),
      textSupport = nativeCanvasSupport 
        && (typeof document.createElement('canvas').getContext('2d').fillText == 'function');
  //I'm setting this based on the fact that ExCanvas provides text support for IE
  //and that as of today iPhone/iPad current text support is lame
  labelType = (!nativeCanvasSupport || (textSupport && !iStuff))? 'Native' : 'HTML';
  nativeTextSupport = labelType == 'Native';
  useGradients = nativeCanvasSupport;
  animate = !(iStuff || !nativeCanvasSupport);
})();

var Log = {
  elem: false,
  write: function(text){
    if (!this.elem) 
      this.elem = document.getElementById('log');
    this.elem.innerHTML = text;
    this.elem.style.left = (500 - this.elem.offsetWidth / 2) + 'px';
  }
};




$(document).ready(function () { 
   $('#button_1').click(function(e){
        e.preventDefault();
        e.stopPropagation();
        arvoreInicial();
   });
});

$(document).ready(function () { 
   $('#button_2').click(function(e){
        e.preventDefault();
        e.stopPropagation();
        arvoreFinal();
   });
});

$(document).ready(function () { 
   $('#button_3').click(function(e){
        e.preventDefault();
        e.stopPropagation();
        arvoreSix();
   });
});

function arvoreSix() {
     $.ajax({
                url  : "http://localhost:8081/colortree/rest/getSixColor",
                type : "GET",
                crossDomain: true,
                contentType: "application/json; charset=utf-8",
                dataType: 'json',
                success: function(data) {
                        //alert("Success: data: " + data);
                        //var json = $.parseJSON(data);
                        console.log(data);
                        //alert(obj);
                        st.loadJSON(data);
                        st.compute();
                        //optional: make a translation of the tree
                        st.geom.translate(new $jit.Complex(-200, 0), "current");
                         //emulate a click on the root node.
                        st.onClick(st.root);
                       // init(json); //this line will call init function with JSON loaded after this call
                },
                error:function (xhr, ajaxOptions, thrownError){
                    alert("Status error: " +xhr.status);
                    alert("Error message: " +thrownError);
                }
         });

    
}


function arvoreInicial() {
     $.ajax({
                url  : "http://localhost:8081/colortree/rest/getTreeInicial",
                type : "GET",
                crossDomain: true,
                contentType: "application/json; charset=utf-8",
                dataType: 'json',
                success: function(data) {
                        //alert("Success: data: " + data);
                        //var json = $.parseJSON(data);
                        console.log(data);
                        //alert(obj);
                        st.loadJSON(data);
                        st.compute();
                        //optional: make a translation of the tree
                        st.geom.translate(new $jit.Complex(-200, 0), "current");
                         //emulate a click on the root node.
                        st.onClick(st.root);
                       // init(json); //this line will call init function with JSON loaded after this call
                },
                error:function (xhr, ajaxOptions, thrownError){
                    alert("Status error: " +xhr.status);
                    alert("Error message: " +thrownError);
                }
         });

    
}

function arvoreFinal() {
     $.ajax({
                url  : "http://localhost:8081/colortree/rest/getTreeFinal",
                type : "GET",
                crossDomain: true,
                contentType: "application/json; charset=utf-8",
                dataType: 'json',
                success: function(data) {
                        //alert("Success: data: " + data);
                        //var json = $.parseJSON(data);
                        console.log(data);
                        //alert(obj);
                        st.loadJSON(data);
                        st.compute();
                        //optional: make a translation of the tree
                        st.geom.translate(new $jit.Complex(-200, 0), "current");
                         //emulate a click on the root node.
                        st.onClick(st.root);
                       // init(json); //this line will call init function with JSON loaded after this call
                },
                error:function (xhr, ajaxOptions, thrownError){
                    alert("Status error: " +xhr.status);
                    alert("Error message: " +thrownError);
                }
         });

}

function init(json){
    var json = json;

    //init data
    // var json = {"id":"0","name":"00111","data":"","children":[{"id":"1","name":"01010","data":"","children":
    // [{"id":"2","name":"00000","data":"","children":null}]},{"id":"3","name":"01100","data":"","children":null}]
    // };

  //end
    //init Spacetree
    //Create a new ST instance
     st = new $jit.ST({
        //id of viz container element
        injectInto: 'infovis',
        //set duration for the animation
        duration: 800,
        //set animation transition type
        transition: $jit.Trans.Quart.easeInOut,
        //set distance between node and its children
        levelDistance: 50,
        //enable panning
        Navigation: {
          enable:true,
          panning:true
        },
        //set node and edge styles
        //set overridable=true for styling individual
        //nodes or edges
        Node: {
            height: 20,
            width: 60,
            type: 'rectangle',
            color: '#aaa',
            overridable: true
        },
        
        Edge: {
            type: 'bezier',
            overridable: true
        },
        
        onBeforeCompute: function(node){
            Log.write("loading " + node.name);
        },
        
        onAfterCompute: function(){
            Log.write("done");
        },
        
        //This method is called on DOM label creation.
        //Use this method to add event handlers and styles to
        //your node.
        onCreateLabel: function(label, node){
            label.id = node.id;            
            label.innerHTML = node.name;
            label.onclick = function(){
            	if(normal != null && normal.checked) {
            	  st.onClick(node.id);
            	} else {
                st.setRoot(node.id, 'animate');
            	}
            };
            //set label styles
            var style = label.style;
            style.width = 60 + 'px';
            style.height = 17 + 'px';            
            style.cursor = 'pointer';
            style.color = '#333';
            style.fontSize = '0.8em';
            style.textAlign= 'center';
            style.paddingTop = '3px';
        },
        
        //This method is called right before plotting
        //a node. It's useful for changing an individual node
        //style properties before plotting it.
        //The data properties prefixed with a dollar
        //sign will override the global node style properties.
        onBeforePlotNode: function(node){
            //add some color to the nodes in the path between the
            //root node and the selected node.
            if (node.selected) {
                node.data.$color = "#23A4FF";
            }
            else {
                delete node.data.$color;
                //if the node belongs to the last plotted level
                if(!node.anySubnode("exist")) {
                    //count children number
                    var count = 0;
                    node.eachSubnode(function(n) { count++; });
                    //assign a node color based on
                    //how many children it has
                    node.data.$color = ['#aaa', '#baa', '#caa', '#daa', '#eaa', '#faa'][count];                    
                }
            }
        },
        
        //This method is called right before plotting
        //an edge. It's useful for changing an individual edge
        //style properties before plotting it.
        //Edge data proprties prefixed with a dollar sign will
        //override the Edge global style properties.
        onBeforePlotLine: function(adj){
            if (adj.nodeFrom.selected && adj.nodeTo.selected) {
                adj.data.$color = "#eed";
                adj.data.$lineWidth = 3;
            }
            else {
                delete adj.data.$color;
                delete adj.data.$lineWidth;
            }
        }
    });

        $.ajax({
                url  : "http://localhost:8081/colortree/rest/getTreeInicial",
                type : "GET",
                crossDomain: true,
                contentType: "application/json; charset=utf-8",
                dataType: 'json',
                success: function(data) {
                        //alert("Success: data: " + data);
                        //var json = $.parseJSON(data);
                        console.log(data);
                        //alert(obj);
                        st.loadJSON(data);
                        st.compute();
                        //optional: make a translation of the tree
                          st.geom.translate(new $jit.Complex(-200, 0), "current");
                         //emulate a click on the root node.
                    st.onClick(st.root);
                       // init(json); //this line will call init function with JSON loaded after this call

                },

                error:function (xhr, ajaxOptions, thrownError){
                    alert("Status error: " +xhr.status);
                    alert("Error message: " +thrownError);
                }
         });


    //load json data
    st.loadJSON(json);
    //compute node positions and layout
    st.compute();
    //optional: make a translation of the tree
    st.geom.translate(new $jit.Complex(-200, 0), "current");
    //emulate a click on the root node.
    st.onClick(st.root);
    //end
    //Add event handlers to switch spacetree orientation.
    var top = $jit.id('r-top'),
        left = $jit.id('r-left'), 
        bottom = $jit.id('r-bottom'), 
        right = $jit.id('r-right'),
        normal = $jit.id('s-normal');
   
    function changeHandler() {
        if(this.checked) {
            top.disabled = bottom.disabled = right.disabled = left.disabled = true;
            st.switchPosition(this.value, "animate", {
                onComplete: function(){
                    top.disabled = bottom.disabled = right.disabled = left.disabled = false;
                }
            });
        }
    };
    
    top.onchange = left.onchange = bottom.onchange = right.onchange = changeHandler;
    //end

}
