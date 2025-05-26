package org.example.jobswapsystem.Service;

import org.example.jobswapsystem.Models.Position;
import org.example.jobswapsystem.Repository.PositionRepository;

import java.util.List;

public class PositionService implements IPositionService {

    private final PositionRepository positionRepository;

    public PositionService() {
        this.positionRepository = new PositionRepository();
    }

    @Override
    public List<Position> getPositions() {
        return positionRepository.getAllPositions();
    }
}
