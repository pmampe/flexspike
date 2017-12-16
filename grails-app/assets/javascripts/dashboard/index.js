var dashboardIndexModule = (function ( $ ) {
  "use strict";

  var initButton12Weeks = function() {
    $("input#show12Weeks").unbind("click");
    $("input#show12Weeks").on("click", function(event) {
        console.log("Button clicked: show 12 weeks");
        $.ajax({
            type: 'POST',
            data: {},
            url: '/dashboard/show12Weeks',
            success: function (data) {
                console.log("success");
                $("div#summary").html(data);
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                console.log("error");
            },
            complete: function() {
                console.log("complete");
            }
        });
    });
  };

  var initButtonAbsence = function() {
    $("input#showAbsence").unbind("click");
    $("input#showAbsence").on("click", function(event) {
        console.log("Button clicked: show absence");
        $.ajax({
            type: 'POST',
            data: {},
            url: '/dashboard/showAbsence',
            success: function (data) {
                console.log("success");
                $("div#summary").html(data);
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                console.log("error");
            },
            complete: function() {
                console.log("complete");
            }
        });
    });
  };

  var initButtonLastMonth = function() {
    $("input#showLastMonth").unbind("click");
    $("input#showLastMonth").on("click", function(event) {
        console.log("Button clicked: show last month");
        $.ajax({
            type: 'POST',
            data: {},
            url: '/dashboard/showLastMonth',
            success: function (data) {
                console.log("success");
                $("div#summary").html(data);
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                console.log("error");
            },
            complete: function() {
                console.log("complete");
            }
        });
    });
  };

  var initButtonMonthly = function() {
    $("input#showMonthly").unbind("click");
    $("input#showMonthly").on("click", function(event) {
        console.log("Button clicked: show monthly");
        $.ajax({
            type: 'POST',
            data: {},
            url: '/dashboard/showMonthly',
            success: function (data) {
                console.log("success");
                $("div#summary").html(data);
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                console.log("error");
            },
            complete: function() {
                console.log("complete");
            }
        });
    });
  };

  var initButtonTimeAdjustments = function() {
    $("input#showTimeAdjustments").unbind("click");
    $("input#showTimeAdjustments").on("click", function(event) {
        console.log("Button clicked: show timeadjustments");
        $.ajax({
            type: 'POST',
            data: {},
            url: '/dashboard/showTimeAdjustments',
            success: function (data) {
                console.log("success");
                $("div#summary").html(data);
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                console.log("error");
            },
            complete: function() {
                console.log("complete");
            }
        });
    });
  };

  var initButtonWorkrates = function() {
    $("input#showWorkRates").unbind("click");
    $("input#showWorkRates").on("click", function(event) {
        console.log("Button clicked: show WorkRates");
        $.ajax({
            type: 'POST',
            data: {},
            url: '/dashboard/showWorkRates',
            success: function (data) {
                console.log("success");
                $("div#summary").html(data);
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                console.log("error");
            },
            complete: function() {
                console.log("complete");
            }
        });
    });
  };

  var initInputComment = function() {
    $("input#comment").unbind("blur");
    $("input#comment").on("blur", function (event) {
        var newValue = $(this).val();
        var flexDateid = $(this).data('flexdateid');
        console.log("Comment: "+newValue+" / "+flexDateid);
    });
  };

  var initInputEndTime = function() {
    $("input#end").unbind("blur");
    $("input#end").on("blur", function (event) {
        var newValue = $(this).val();
        var flexDateid = $(this).data('flexdateid');
        var reg = new RegExp('^[0-2][0-9]:[0-5][0-9]$');
        console.log("EndTime: "+newValue+" / "+flexDateid+" / "+reg.test(newValue));
        if(reg.test(newValue)) {
            $("strong#message").text("");
        } else {
            $("strong#message").text("EndTime has bad format ...");
        }
    });
  };

  var initInputLunchLength = function() {
    $("input#lunch").unbind("blur");
    $("input#lunch").on("blur", function (event) {
        var newValue = $(this).val();
        var flexDateid = $(this).data('flexdateid');
        var reg = new RegExp('^[0-0][0-5]:[0-5][0-9]$');
        console.log("Lunch: "+newValue+" / "+flexDateid+" / "+reg.test(newValue));
        if(reg.test(newValue)) {
            $("strong#message").text("");
        } else {
            $("strong#message").text("LunchLength has bad format ...");
        }
    });
  };

  var initInputStartTime = function() {
      $("input#start").unbind("blur");
      $("input#start").on("blur", function (event) {
          var newValue = $(this).val();
          var flexDateid = $(this).data('flexdateid');
          var reg = new RegExp('^[0-2][0-9]:[0-5][0-9]$');
          console.log("StartTime: "+newValue+" / "+flexDateid+" / "+reg.test(newValue));
          if(reg.test(newValue)) {
              $("strong#message").text("");
          } else {
              $("strong#message").text("StartTime has bad format ...");
          }
      });
  };

  var initSelectDate = function() {
    $("select#date").unbind("change");
    $("select#date").on("change", function(event) {
        var selectedDate = $(this).val();
        console.log("Date changed: "+selectedDate);
        $.ajax({
            type: 'POST',
            data: {id: selectedDate},
            url: '/dashboard/selectOtherDate',
            success: function (data) {
                console.log("success");
                $("div#reportTime").html(data);
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                console.log("error");
            },
            complete: function() {
                console.log("complete");
                initInputComment();
                initInputEndTime();
                initInputLunchLength();
                initInputStartTime();
            }
        });
    });
  };

  var initModule = function( $container ){
      initButton12Weeks();
      initButtonAbsence();
      initButtonLastMonth();
      initButtonMonthly();
      initButtonTimeAdjustments();
      initButtonWorkrates();
      initSelectDate();
  };

  return {initModule: initModule};
}(jQuery));

jQuery(document).ready(
  function() {
    dashboardIndexModule.initModule(jQuery("body"));
  }
);