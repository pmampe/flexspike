var dashboardWorkRateModule = (function ( $ ) {
  "use strict";

  var initInputEndDate = function() {
    $("input#enddate").unbind("blur");
    $("input#enddate").on("blur", function (event) {
        var newValue = $(this).val();
        var reg = new RegExp('^[0-9][0-9]-[0-1][0-9]-[0-3][0-9]$');
        console.log("EndTime: "+newValue+" // "+reg.test(newValue));
        if(reg.test(newValue)) {
            $("strong#message1").text("");
            if($("input#startdate").val().trim().length>0 && $("input#workrate").val().trim().length>0) {
                $("input#saveWorkRate").prop('disabled', false);
            }
        } else {
            $("strong#message1").text("Bad format");
            $("input#saveWorkRate").prop('disabled', true);
        }
    });
  };

  var initInputStartDate = function() {
    $("input#startdate").unbind("blur");
    $("input#startdate").on("blur", function (event) {
        var newValue = $(this).val();
        var reg = new RegExp('^[0-9][0-9]-[0-1][0-9]-[0-3][0-9]$');
        console.log("StartTime: "+newValue+" // "+reg.test(newValue));
        if(reg.test(newValue)) {
            $("strong#message0").text("");
            if($("input#enddate").val().trim().length>0 && $("input#workrate").val().trim().length>0) {
                $("input#saveWorkRate").prop('disabled', false);
            }
        } else {
            $("strong#message0").text("Bad format");
            $("input#saveWorkRate").prop('disabled', true);
        }
    });
  };

  var initInputWorkRate = function() {
    $("input#workrate").unbind("blur");
    $("input#workrate").on("blur", function (event) {
        var newValue = $(this).val();
        var reg1 = new RegExp('^[0-9]$');
        var reg2 = new RegExp('^[0-9][0-9]$');
        var reg3 = new RegExp('^[0-1][0-9][0-9]$');
        var checkNumeric = reg1.test(newValue)||reg2.test(newValue)||reg3.test(newValue);
        console.log("StartTime: "+newValue+" // "+checkNumeric);
        if(checkNumeric) {
            if(newValue<1) {
                $("strong#message2").text("To small");
                $("input#saveWorkRate").prop('disabled', true);
            } else if(newValue>99) {
                $("strong#message2").text("To large");
                $("input#saveWorkRate").prop('disabled', true);
            } else {
                $("strong#message2").text("");
                if($("input#enddate").val().trim().length>0 && $("input#startdate").val().trim().length>0) {
                    $("input#saveWorkRate").prop('disabled', false);
                }
            }
        } else {
            $("strong#message2").text("Bad format");
            $("input#saveWorkRate").prop('disabled', true);
        }
    });
  };

  var initModule = function( $container ){
      initInputEndDate();
      initInputStartDate();
      initInputWorkRate();
  };

  return {initModule: initModule};
}(jQuery));

jQuery(document).ready(
  function() {
    dashboardWorkRateModule.initModule(jQuery("body"));
  }
);