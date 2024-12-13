package gcu.smapill_back.converter;

import gcu.smapill_back.domain.Prescription;
import gcu.smapill_back.domain.Schedule;
import gcu.smapill_back.domain.User;
import gcu.smapill_back.domain.enums.TimeOfDay;
import gcu.smapill_back.web.dto.PrescriptionRequestDTO;
import gcu.smapill_back.web.dto.PrescriptionResponseDTO;
import gcu.smapill_back.web.dto.ScheduleRequestDTO;
import gcu.smapill_back.web.dto.ScheduleResponseDTO;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ScheduleConverter {
    public static Schedule toSchedule(LocalDate currentDate, TimeOfDay timeOfDay, Prescription prescription, User user) {
        return Schedule.builder()
                .name(prescription.getName())
                .scheduleDate(currentDate)
                .timeOfDay(timeOfDay)
                .dosage(prescription.getDosage())
                .prescription(prescription)
                .user(user)
                .build();
    }

    public static Schedule toScheduleResultDTO(ScheduleRequestDTO.CreateScheduleDTO request) {
        return Schedule.builder()
                .name(request.getName())
                .scheduleDate(request.getScheduleDate())
                .timeOfDay(request.getTimeOfDay())
                .dosage(request.getDosage())
                .build();
    }

    public static ScheduleResponseDTO.CreateScheduleResultDTO toCreateResultDTO(Schedule schedule) {
        return ScheduleResponseDTO.CreateScheduleResultDTO.builder()
                .scheduleId(schedule.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static ScheduleResponseDTO.UpdateScheduleResultDTO toUpdateScheduleResultDTO(Schedule schedule) {
        return ScheduleResponseDTO.UpdateScheduleResultDTO.builder()
                .scheduleId(schedule.getId())
                .isTaken(schedule.isTaken())
                .updatedAt(schedule.getUpdatedAt())
                .build();
    }

    public static ScheduleResponseDTO.GetScheduleResultListDTO toGetScheduleResultListDTO(List<Schedule> scheduleList, TimeOfDay timeOfDay) {
        List<ScheduleResponseDTO.GetScheduleResultDTO> resultDTOList = scheduleList.stream()
                .map(ScheduleConverter::toGetScheduleResultDTO).collect(Collectors.toList());

        return ScheduleResponseDTO.GetScheduleResultListDTO.builder()
                .schedule(resultDTOList)
                .build();
    }

    public static ScheduleResponseDTO.GetScheduleResultDTO toGetScheduleResultDTO(Schedule schedule) {
        return ScheduleResponseDTO.GetScheduleResultDTO.builder()
                .scheduleId(schedule.getId())
                .name(schedule.getName())
                .isTaken(schedule.isTaken())
                .dosage(schedule.getDosage())
                .build();
    }
}
