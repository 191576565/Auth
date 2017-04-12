var Script = function () {


    /* initialize the external events
     -----------------------------------------------------------------*/

    

    /* initialize the calendar
     -----------------------------------------------------------------*/

    var date = new Date();
    var d = date.getDate();
    var m = date.getMonth();
    var y = date.getFullYear();

    $('#calendar').fullCalendar({
        header: {
            left: 'prev,next today',
            center: 'title',
            right: 'month,basicWeek,basicDay'
        },
        monthNames: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
		monthNamesShort: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
		dayNames: ["周日", "周一", "周二", "周三", "周四", "周五", "周六"],
		dayNamesShort: ["周日", "周一", "周二", "周三", "周四", "周五", "周六"],
		today: ["今天"],
		firstDay: 1,
		buttonText: {
			today: '今天',
			month: '月',
			week: '周',
			day: '日',
			prev: '上一月',
			next: '下一月'
		},
		timeFormat: {
			'': 'H:mm{-H:mm}'
		},
		columnFormat: {
			month: 'dddd',
			week: 'dddd M-d',
			day: 'dddd M-d'
		},
		titleFormat: {
			month: 'yyyy年 MMMM',
			week: "[yyyy年] MMMM d日 { '&#8212;' [yyyy年] MMMM d日}",
			day: 'yyyy年 MMMMd日 dddd'
		},
        editable: true,
        droppable: true, // this allows things to be dropped onto the calendar !!!
        events: [
            {
                title: '今日事,今日毕',
                start: new Date(y, m, d)
            }
        ]
    });


}();