package com.v1690117.pnt.service.controller;

import com.v1690117.pnt.service.dto.SetDto;
import com.v1690117.pnt.service.repository.SetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RequiredArgsConstructor
@RestController
public class SetController {
    private final SetRepository setRepository;

    @PatchMapping("/sets/{setId}")
    public ResponseEntity<?> updateSet(@PathVariable Long setId, @RequestBody SetDto setDto) {
        var set = setRepository.findById(setId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Set not found"));
        if (setDto.getLoad() != null) {
            set.setLoad(setDto.getLoad());
        }
        if (setDto.getReps() != null) {
            set.setReps(setDto.getReps());
        }
        if (setDto.getRest() != null) {
            set.setRest(setDto.getRest());
        }
        if (setDto.getCompleted() != null) {
            set.setCompleted(setDto.getCompleted());
        }
        setRepository.save(set);
        return ResponseEntity.ok().build();
    }
}
