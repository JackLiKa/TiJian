package com.example.jnu_tijian.controller;



import com.example.jnu_tijian.dto.CalendarRequestDto;
import com.example.jnu_tijian.dto.ResponseObject;
import com.example.jnu_tijian.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalendarController {

    /**
     * 请求对象：模拟
     * 参数：年份  月份  医院编号
     *
     *
     * 响应对象：前端请求日历后需要的数据的结构
     *
     * 一是：  对应年份和月份的日历  2025年7月
     *
     * 二是：  对应挂号规则的  余号信息
     *  挂号只能挂明天开始30天内的号
     *
     */
    @Autowired
    CalendarService calendarService;

    ///api/listCalendar",{hpId:state.hpId,year:state.currYear,month:state.currMonth}
    @RequestMapping("/listCalendar")
    public ResponseObject listCalendar(@RequestBody CalendarRequestDto calendarRequestDto){
//        System.out.println(calendarRequestDto.getYear());
//        System.out.println(calendarRequestDto.getMonth());
//        System.out.println(calendarRequestDto.getHpId());
//        System.out.println("以上是获取的信息");
//        return calendarService.listAppointmentCalendar(calendarRequestDto);
        return new ResponseObject(ResponseObject.SUCCESS_STATUS,ResponseObject.SUCCESS_DESC,
                calendarService.listAppointmentCalendar(calendarRequestDto));
    }

       /* @Autowired
        CalendarService calendarService;

        @RequestMapping("/listAppoinmentCalendar")
        public ApiResponse listAppoinmentCalendar(@RequestBody CalendarRequestDto calendarRequestDto, HttpServletRequest request){
            return new ApiResponse(ApiResponse.SUCCESS_STATUS,ApiResponse.SUCCESS_DESC,calendarService.listAppointmentCalendar(calendarRequestDto));
        }

        @RequestMapping("/listAppoinmentCalendar2")
        public ApiResponse list2(){
            return new ApiResponse(ApiResponse.SUCCESS_STATUS,ApiResponse.SUCCESS_DESC,calendarService.listAppointmentCalendar(calendarRequestDto));
        }*/


}
