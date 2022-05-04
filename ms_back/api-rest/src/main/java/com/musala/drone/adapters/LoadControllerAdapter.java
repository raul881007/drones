package com.musala.drone.adapters;

import com.musala.drone.domain.Load;
import com.musala.drone.domain.ErrorResponse;
import com.musala.drone.exceptions.LoadNotFoundException;
import com.musala.drone.ports.in.LoadServicePort;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;

@Slf4j
@RestController
@Data
@AllArgsConstructor
@RequestMapping("/loads/")
public class LoadControllerAdapter {


    private final LoadServicePort loadServicePort;

    /**
     * Method that creates a drone with the data sent by the user
     * @param load
     * @return ResponseEntity.created() with a new drone data or BadRequest if something is worng
     */
    @PostMapping
    private ResponseEntity<Load> createLoad(@RequestBody Load load){
        return ResponseEntity.status(HttpStatus.CREATED).body(loadServicePort.createLoad(load));
    }

    /**
     * Method that gets the drone data
     * @param id load id
     * @return New load data
     */
    @GetMapping(value="{id}")
    @ResponseStatus(HttpStatus.OK)
    private Object getLoad(@PathVariable("id") Long id) {
        try {
            return loadServicePort.getLoad(id);
        } catch (LoadNotFoundException e) {
            return ErrorResponse.builder()
                    .timestamp(LocalDateTime.now())
                    .error("There is an error")
                    .message(e.getMessage())
                    .build();
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public Object handleValidationExceptions(
            Exception ex) {
        return ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .error("There is an error")
                .message(ex.getMessage())
                .build();
    }


}
