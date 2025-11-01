package com.api.funcion.service;
import com.api.funcion.dto.SumaResponseDto;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SumaService {

    public SumaResponseDto encontrarSumaDos(List<Integer> numeros, Integer objetivo) {

        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < numeros.size(); i++) {
            int complement = objetivo - numeros.get(i);
            if (map.containsKey(complement)) {
                return new SumaResponseDto(map.get(complement), i);
            }
            map.put(numeros.get(i), i);
        }
        return null;
    }
}
