var XPath = function() {

var datatable_cell_class = 'aw_widgets_datatable_ContentPanel_cell';
var datatable_row_class = 'aw_widgets_datatable_ContentPanel_cell';

var thisObject = {
    evalDump : function(rule) {
      thisObject.dump(thisObject.eval(rule));
    },
    eval : function(rule) {
        return document.evaluate( rule, document, null, XPathResult.ANY_TYPE, null );
    },
    extractRow : function(cellValue) {

       var rule = "";
           rule+= "//*[";
       //"./contains(concat(' ', normalize-space(@class), ' '), ' " + datatable_row_class + " ' ) and ";

       rule += "contains(concat(' ', normalize-space(@class), ' '), ' " + datatable_cell_class+ " ' )/text() = '" + cellValue + "']";
       thisObject.print(rule);
       return thisObject.eval(rule);
    },

    getByClass : function(className) {
        var rule = "//*[contains(concat(' ', normalize-space(@class), ' '), ' " + className + " ' )]";
        thisObject.eval(rule);
    },
    dump : function(iterator) {
        try {
          var thisNode = iterator.iterateNext();

          while (thisNode) {
            thisObject.print( thisNode.textContent );
            thisNode = iterator.iterateNext();
          }
        }
        catch (e) {
          thisObject.print( 'Error: Document tree modified during iteration ' + e );
        }
    },
    print : function() {
        console.log.apply(console, arguments);
    }
    };
    return thisObject;
}
;
var xpath = XPath();
//xpath.dump(xpath.getByClass());
var result = xpath.extractRow("201605133");
xpath.dump(result);



