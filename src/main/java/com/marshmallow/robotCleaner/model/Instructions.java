package com.marshmallow.robotCleaner.model;

import lombok.*;

import javax.validation.Valid;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Instructions {

    private List<Integer> areaSize;

    private List<Integer> startingPosition;

    private List<List<Integer>> oilPatches;

    private String navigationInstructions;

}
