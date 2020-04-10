package com.xjtu.happy.ticket.service.management;

import com.xjtu.happy.ticket.bean.Train;
import com.xjtu.happy.ticket.mapper.management.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainService {
    @Autowired
    TrainMapper trainMapper;

    public boolean InsertTrain(Train train){
        if (trainMapper.InsertTrain(train) >= 1)
            return true;
        else
            return false;
    }
    public Train FindTrainById(String trainId){
        return trainMapper.FindTrainById(trainId);
    }
    public List<Train> FindAllTrains(){
        return trainMapper.FindAllTrains();
    }
    public int CountOfTrains() {
        return trainMapper.CountOfTrains();
    }
}