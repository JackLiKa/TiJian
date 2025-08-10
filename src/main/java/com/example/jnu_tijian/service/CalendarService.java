package com.example.jnu_tijian.service;

import com.example.jnu_tijian.dto.CalendarRequestDto;
import com.example.jnu_tijian.dto.CalendarResponseDto;
import com.example.jnu_tijian.dto.OrdersMapperDto;
import com.example.jnu_tijian.mapper.HospitalMapper;
import com.example.jnu_tijian.mapper.OrdersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class CalendarService{
    @Autowired
    OrdersMapper ordersMapper;
    @Autowired
    HospitalMapper hospitalMapper;

    //生成前端界面需要的预约日历数据

    public List<CalendarResponseDto> listAppointmentCalendar(CalendarRequestDto calendarRequestDto){

        //万年历：某年某月的日历
        List<CalendarResponseDto> calendarResponseDtoList=getCurrentCalendarList(calendarRequestDto.getYear(),calendarRequestDto.getMonth());

        //30天日历：可以预约的未来30天的日历
        List<CalendarResponseDto> calendarResponseDto30=getCalendarList30(calendarRequestDto.getHpId());

        for(CalendarResponseDto cd:calendarResponseDtoList){   //12.1-12.31  30次

            for(CalendarResponseDto cdAppointment:calendarResponseDto30){  //12.30--1.28  30次
                if(cdAppointment!=null&&cdAppointment.getYmd()!=null){
                    if(cdAppointment.getYmd().equals(cd.getYmd())){
                        cd.setTotal(cdAppointment.getTotal());
                        cd.setRemainder(cdAppointment.getRemainder());
                        cd.setExisting(cdAppointment.getExisting());
                    }
                }

            }
        }
        return calendarResponseDtoList;
    }







    public static void main(String[] args) {
        /*List<CalendarResponseDto> list=new CalendarServiceImpl().getCurrentCalendarList(2025,8);

        for(CalendarResponseDto cd:list){
            System.out.println(cd.getYmd());
        }*/

        List<CalendarResponseDto> calendarResponseDtos= new CalendarService().getCalendarList30(2);
        for(CalendarResponseDto cr:calendarResponseDtos){
            System.out.println(cr.getYmd()+","+cr.getTotal()+","+cr.getExisting()+","+cr.getRemainder());
        }
    }

    //获取当前年和当前月的日历
    private List<CalendarResponseDto> getCurrentCalendarList(Integer year, Integer month){
        List<CalendarResponseDto> currentCalendar = new ArrayList<>();

        //做万年历
        boolean isRun = false;
        if((year%4==0&&year%100!=0) || (year%400==0)) {
            isRun = true;
        }
        //计算从1900-01-01到现在的天数
        int totalDays = 0;
        for(int i=1900;i<year;i++) {
            if((i%4==0&&i%100!=0) || (i%400==0)) {
                totalDays += 366;
            }else {
                totalDays += 365;
            }
        }
        int beforeDays = 0;
        int currentDays = 0;
        for(int j=1;j<=month;j++) {
            switch(j) {
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    currentDays = 31;
                    break;
                case 4:
                case 6:
                case 9:
                case 11:
                    currentDays = 30;
                    break;
                case 2:
                    if(isRun) {
                        currentDays = 29;
                    }else {
                        currentDays = 28;
                    }
                    break;
            }
            if(j<month) {
                beforeDays += currentDays;
            }
        }
        totalDays += beforeDays;
        int firstDayOfMonth = totalDays%7 + 1;
        if(firstDayOfMonth == 7) {
            firstDayOfMonth = 0;
        }
        for(int k=0;k<firstDayOfMonth;k++) {
            currentCalendar.add(new CalendarResponseDto());
        }
        for(int i=1;i<=currentDays;i++) {
            String m = month<10?"0"+month:month+"";
            String d = i<10?"0"+i:i+"";
            currentCalendar.add(new CalendarResponseDto(year+"-"+m+"-"+d));
        }
        return currentCalendar;
    }



    public List<CalendarResponseDto> getCalendarList30(Integer hpId) {

        //定义两个工具类
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar=Calendar.getInstance();

        //做一个临时集合，作为去Orders表中查询的参数
        List<OrdersMapperDto> dtoList=new ArrayList<>();

        for(int i=0;i<30;i++){  //就30天
            OrdersMapperDto ordersMapperDto=new OrdersMapperDto();
            calendar.add(Calendar.DATE,1);
            ordersMapperDto.setDate(sdf.format(calendar.getTime()));  //2024-12-27
            ordersMapperDto.setHpId(hpId);

            dtoList.add(ordersMapperDto);
        }

        //来，我们去数据库查询所选医院可以预约的30天然后每天的已经约号的数量（结果中有日期和已经约号数量）
        List<CalendarResponseDto> calendarResponseDtoList=ordersMapper.listHospitalAppointmentNumber(dtoList);

        // 获取所选医院每天的约号最大数量
        String[] ruleArr=hospitalMapper.selectByPrimaryKey(hpId).getRule().split(",");//  【0，200，200，200，200.，200，100】

//        System.out.println(ruleArr.toString());

        //对前面的集合进行遍历，填充对象的另外两个数据：可以预约的总数，余号数量
        for(CalendarResponseDto cd:calendarResponseDtoList){
            try {
                calendar.setTime(sdf.parse(cd.getYmd()));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            int total=Integer.parseInt(ruleArr[calendar.get(Calendar.DAY_OF_WEEK)-1]);  //200

            cd.setTotal(total);
            cd.setRemainder(total-cd.getExisting());
        }
        System.out.println(calendarResponseDtoList.toString());
        return calendarResponseDtoList;
    }
}