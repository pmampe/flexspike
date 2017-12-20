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

  var initButtonCame = function() {
    $("input#came").unbind("click");
    $("input#came").on("click", function(event) {
        var flexdateid = $(this).data('flexdateid');
        var starttime = $("input#start").val();
        var reg = new RegExp('^[0-2][0-9]:[0-5][0-9]$');
        console.log("Button clicked: came "+flexdateid+" / "+starttime+" / "+reg.test(starttime));
        if(!reg.test(starttime)) {
            starttime="";
        }
        $.ajax({
            type: 'POST',
            data: {id: flexdateid, starttime: starttime},
            url: '/dashboard/reportStartTime',
            success: function (data) {
                console.log("success");
                $("div#reportTime").html(data);
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                console.log("error");
            },
            complete: function() {
                console.log("complete");
                initReportFields();
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

  var initButtonLeft = function() {
    $("input#left").unbind("click");
    $("input#left").on("click", function(event) {
        var flexdateid = $(this).data('flexdateid');
        var endtime = $("input#end").val();
        var reg = new RegExp('^[0-2][0-9]:[0-5][0-9]$');
        console.log("Button clicked: came "+flexdateid+" / "+endtime+" / "+reg.test(endtime));
        if(!reg.test(endtime)) {
            endtime="";
        }
        $.ajax({
            type: 'POST',
            data: {id: flexdateid, endtime: endtime},
            url: '/dashboard/reportEndTime',
            success: function (data) {
                console.log("success");
                $("div#reportTime").html(data);
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                console.log("error");
            },
            complete: function() {
                console.log("complete");
                initReportFields();
            }
        });
    });
  };

  var initButtonLunch = function() {
    $("input#lunch").unbind("click");
    $("input#lunch").on("click", function(event) {
        var flexdateid = $(this).data('flexdateid');
        var lunchlength = $("input#lunch").val();
        var reg = new RegExp('^[0-2][0-9]:[0-5][0-9]$');
        console.log("Button clicked: lunch "+flexdateid+" / "+lunchlength+" / "+reg.test(lunchlength));
        if(!reg.test(lunchlength)) {
            lunchlength="";
        }
        $.ajax({
            type: 'POST',
            data: {id: flexdateid, lunchlength: lunchlength},
            url: '/dashboard/reportLunchLength',
            success: function (data) {
                console.log("success");
                $("div#reportTime").html(data);
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                console.log("error");
            },
            complete: function() {
                console.log("complete");
                initReportFields();
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

  var initCheckBoxAllDay = function() {
      $("input#flexAllDay").unbind("click");
      $("input#flexAllDay").on("click", function(event) {
          var flexDateid = $(this).data('flexdateid');
          var isChecked = $(this).is(':checked')
          console.log("Comment: "+isChecked+" / "+flexDateid);
          $.ajax({
              type: 'POST',
              data: {id: flexDateid, isChecked: isChecked},
              url: '/dashboard/reportFullDayAbsence',
              success: function (data) {
                  console.log("success");
                  $("div#reportTime").html(data);
              },
              error: function (XMLHttpRequest, textStatus, errorThrown) {
                  console.log("error");
              },
              complete: function() {
                  console.log("complete");
                  initReportFields();
              }
          });
      });
  };

  var initInputComment = function() {
    $("input#comment4day").unbind("blur");
    $("input#comment4day").on("blur", function (event) {
        var newValue = $(this).val();
        var flexDateid = $(this).data('flexdateid');
        console.log("Comment: "+newValue+" / "+flexDateid);
        $.ajax({
            type: 'POST',
            data: {id: flexDateid, comment: newValue},
            url: '/dashboard/reportComment',
            success: function (data) {
                console.log("success");
                $("div#reportTime").html(data);
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                console.log("error");
            },
            complete: function() {
                console.log("complete");
                initReportFields();
            }
        });
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
            $.ajax({
                type: 'POST',
                data: {id: flexDateid, endtime: newValue},
                url: '/dashboard/reportEndTime',
                success: function (data) {
                    console.log("success");
                    $("div#reportTime").html(data);
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    console.log("error");
                },
                complete: function() {
                    console.log("complete");
                    initReportFields();
                }
            });
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
            var flexdateid = $(this).data('flexdateid');
            $.ajax({
                    type: 'POST',
                    data: {id: flexdateid, lunchlength: newValue},
                    url: '/dashboard/reportLunchLength',
                    success: function (data) {
                        console.log("success");
                        $("div#reportTime").html(data);
                    },
                    error: function (XMLHttpRequest, textStatus, errorThrown) {
                        console.log("error");
                    },
                    complete: function() {
                        console.log("complete");
                        initReportFields();
                    }
            });
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
                var flexdateid = $(this).data('flexdateid');
                $.ajax({
                    type: 'POST',
                    data: {id: flexdateid, starttime: newValue},
                    url: '/dashboard/reportStartTime',
                    success: function (data) {
                        console.log("success");
                        $("div#reportTime").html(data);
                    },
                    error: function (XMLHttpRequest, textStatus, errorThrown) {
                        console.log("error");
                    },
                    complete: function() {
                        console.log("complete");
                        initReportFields();
                    }
                });
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
                initReportFields();
            }
        });
    });
  };

  var initReportFields = function() {
        initButtonCame();
        initButtonLeft();
        initButtonLunch();
        initCheckBoxAllDay();
        initInputComment();
        initInputEndTime();
        initInputLunchLength();
        initInputStartTime();
        initSelectDate();
  };

  var initModule = function( $container ){
      initButton12Weeks();
      initButtonAbsence();
      initButtonLastMonth();
      initButtonMonthly();
      initButtonTimeAdjustments();
      initButtonWorkrates();
      initReportFields();
  };

  return {initModule: initModule};
}(jQuery));

jQuery(document).ready(
  function() {
    dashboardIndexModule.initModule(jQuery("body"));
  }
);