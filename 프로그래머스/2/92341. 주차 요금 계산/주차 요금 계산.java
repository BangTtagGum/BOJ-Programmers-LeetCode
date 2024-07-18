import java.util.*;
import java.util.Map.Entry;
class Solution {
    public int[] solution(int[] fees, String[] records) {
        int[] answer = {};

        Map<String,String> parkingLog = new HashMap<>();
        Map<String,Integer> parkingTime = new HashMap<>();
        for(String s : records){
            String[] log = s.split(" ");
            if(log[2].equals("IN")){
                parkingLog.put(log[1], log[0]);
            } else{
                String carNum = log[1];
                String inTimeLog = parkingLog.get(carNum);
                String outTimeLog = log[0];
                parkingTime.put(carNum,
                        parkingTime.getOrDefault(carNum, 0) + getTime(outTimeLog) - getTime(
                                inTimeLog));
                parkingLog.remove(carNum);
            }

        }

        for(Entry<String,String> e : parkingLog.entrySet()){
            String carNum = e.getKey();
            String inTimeLog = e.getValue();
            String outTimeLog = "23:59";
            parkingTime.put(carNum,
                    parkingTime.getOrDefault(carNum, 0) + getTime(outTimeLog) - getTime(
                            inTimeLog));
        }

        Set<String> cars = parkingTime.keySet();
        List<String> list = new ArrayList<>(cars);
        Collections.sort(list);


        return list.stream().map(x -> getFee(parkingTime.get(x),fees)).mapToInt(Integer::valueOf).toArray();
    }

    private int getTime(String timeLog){
        String[] time = timeLog.split(":");
        return Integer.parseInt(time[0]) * 60 + Integer.parseInt(time[1]);
    }

    private int getFee(int parkingTime,  int[] fees){

        // 기본요금
        int parkingFee;
        if(parkingTime <= fees[0]){
            parkingFee = fees[1];
        } else{
            parkingTime -= fees[0];

            parkingFee = fees[1] + (parkingTime / fees[2]) * fees[3];
            // 올림
            if(parkingTime % fees[2] > 0){
                parkingFee += fees[3];
            }
        }

        return parkingFee;
    }
}