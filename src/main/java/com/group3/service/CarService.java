package com.group3.service;


import com.group3.CarNotFoundException;
import com.group3.dto.Car;
import com.group3.enums.GearType;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.common.COSArrayList;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class CarService {

    public Car getCar(Long id) throws IOException {

        List<Car> cars = getCars();

        Optional<Car> storedCar = cars.stream().filter(car -> Objects.equals(car.getId(), id)).findFirst();

        if(storedCar.isEmpty()){
            throw new CarNotFoundException("Car not found with specified id :" + id);
        }

        Car car = storedCar.get();
        log.info("Car has been founded " + car);
        return car;
    }

    public List<Car> getCars() throws IOException {

        String filePath = "src/main/resources/Car.xlsx";
        FileInputStream file = new FileInputStream(filePath);

        Workbook workbook = WorkbookFactory.create(file);
        Sheet sheet = workbook.getSheetAt(0);

        List<Car> allCars = new ArrayList<>();

        for (Row row : sheet) {

            if (row.getRowNum() == 0) { // skip header
                continue;
            }

            Car build = Car.builder()
                    .id((long) row.getCell(0).getNumericCellValue())
                    .price(BigDecimal.valueOf(row.getCell(1).getNumericCellValue()))
                    .make(row.getCell(2).getStringCellValue())
                    .model(row.getCell(3).getStringCellValue())
                    .year(String.valueOf(row.getCell(4).getNumericCellValue()))
                    .gearType(GearType.valueOf(row.getCell(5).getStringCellValue()))
                    .color(row.getCell(6).getStringCellValue())
                    .build();
            allCars.add(build);

        }
        workbook.close();

        log.info("Founded car count is {}", allCars.size());
        return allCars;

    }

}
