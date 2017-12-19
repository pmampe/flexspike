var dashboardTimeAdjustmentModule = (function ( $ ) {
  "use strict";

  var initInputAdjustment = function() {
    $("input#adjustment").unbind("blur");
    $("input#adjustment").on("blur", function (event) {
        var newValue = $(this).val();
        if(newValue.indexOf("-")==0) {
            newValue = newValue.substring(1);
        } else {
            if(newValue.indexOf("+")==0) {
                newValue = newValue.substring(1);
            }
        }
        var reg0 = new RegExp('^[0-9]:[0-5][0-9]$');
        var reg1 = new RegExp('^[0-9][0-9]:[0-5][0-9]$');
        var reg2 = new RegExp('^[0-9][0-9][0-9]:[0-5][0-9]$');
        var checkValid = reg0.test(newValue)||reg1.test(newValue)||reg2.test(newValue);
        console.log("DeltaTime: "+newValue+" / "+checkValid);
        if(checkValid) {
            $("strong#message").text("");
            $("input#saveTimeAdjustment").prop('disabled', false);
        } else {
            $("strong#message").text("Bad format");
            $("input#saveTimeAdjustment").prop('disabled', true);
        }
    });
  };

  var initModule = function( $container ){
      initInputAdjustment();
  };

  return {initModule: initModule};
}(jQuery));

jQuery(document).ready(
  function() {
    dashboardTimeAdjustmentModule.initModule(jQuery("body"));
  }
);