package com.example.ServiceProvider.controller;

import com.example.ServiceProvider.dto.request.DistrictAddressRequest;
import com.example.ServiceProvider.dto.request.DivisionAddressRequest;
import com.example.ServiceProvider.dto.response.DistrictAddressResponse;
import com.example.ServiceProvider.dto.response.DivisionAddressResponse;
import com.example.ServiceProvider.service.DivisionAddressService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/divisions")
public class DivisionAddressController {
    private DivisionAddressService divisionAddressService;

    @PostMapping
    public DivisionAddressResponse createDivision(@RequestBody DivisionAddressRequest divisionAddressRequest) {
        return divisionAddressService.createDivision(divisionAddressRequest);
    }

    @GetMapping
    public List<DivisionAddressResponse> getDivisionList(){
        return divisionAddressService.getDivisionList();
    }

    @PutMapping("/{division-uuid}")
    public DivisionAddressResponse editDivision(@PathVariable("division-uuid") String uuid,
                                                @RequestBody DivisionAddressRequest divisionAddressRequest){
        return divisionAddressService.editDivision(uuid, divisionAddressRequest);
    }

    @DeleteMapping("/{division-uuid}")
    public boolean deleteDivision(@PathVariable("division-uuid") String uuid) {
        return divisionAddressService.deleteDivision(uuid);
    }
}
