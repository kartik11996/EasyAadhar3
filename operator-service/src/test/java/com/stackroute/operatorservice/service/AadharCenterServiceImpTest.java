package com.stackroute.operatorservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.stackroute.operatorservice.exception.BusinessException;
import com.stackroute.operatorservice.model.AadharCenterRegister;
import com.stackroute.operatorservice.model.Appointment;
import com.stackroute.operatorservice.model.AppointmentStatus;
import com.stackroute.operatorservice.repository.AadharCenterRepo;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.MultipartFile;

@ContextConfiguration(classes = {AadharCenterServiceImp.class})
@ExtendWith(SpringExtension.class)
class AadharCenterServiceImpTest {
    @MockBean
    private AadharCenterRepo aadharCenterRepo;

    @Autowired
    private AadharCenterServiceImp aadharCenterServiceImp;

    @Test
    void testCreate2() throws IOException {
        AadharCenterRegister aadharCenterRegister = new AadharCenterRegister();
        when(aadharCenterRepo.save((AadharCenterRegister) any())).thenReturn(aadharCenterRegister);

        AadharCenterRegister aadharCenterRegister1 = new AadharCenterRegister();
        aadharCenterRegister1.setCenterName("Center Name");
        assertSame(aadharCenterRegister, aadharCenterServiceImp.create(aadharCenterRegister1,
                new MockMultipartFile("Name", new ByteArrayInputStream("AAAAAAAA".getBytes("UTF-8")))));
        verify(aadharCenterRepo).save((AadharCenterRegister) any());
        assertEquals(8, aadharCenterRegister1.getVisualsOfCenter().length);
    }

    @Test
    void testCreate3() throws IOException {
        when(aadharCenterRepo.save((AadharCenterRegister) any())).thenReturn(new AadharCenterRegister());

        AadharCenterRegister aadharCenterRegister = new AadharCenterRegister();
        aadharCenterRegister.setCenterName("");
        assertThrows(BusinessException.class, () -> aadharCenterServiceImp.create(aadharCenterRegister,
                new MockMultipartFile("Name", new ByteArrayInputStream("AAAAAAAA".getBytes("UTF-8")))));
    }


    @Test
    void testCreate5() throws IOException {
        when(aadharCenterRepo.save((AadharCenterRegister) any()))
                .thenThrow(new BusinessException("An error occurred", "An error occurred"));

        AadharCenterRegister aadharCenterRegister = new AadharCenterRegister();
        aadharCenterRegister.setCenterName("Center Name");
        assertThrows(BusinessException.class, () -> aadharCenterServiceImp.create(aadharCenterRegister,
                new MockMultipartFile("Name", new ByteArrayInputStream("AAAAAAAA".getBytes("UTF-8")))));
        verify(aadharCenterRepo).save((AadharCenterRegister) any());
    }


    @Test
    void testUpdate3() throws BusinessException, IOException {
        AadharCenterRegister aadharCenterRegister = new AadharCenterRegister();
        when(aadharCenterRepo.save((AadharCenterRegister) any())).thenReturn(aadharCenterRegister);
        when(aadharCenterRepo.findById((String) any())).thenReturn(Optional.of(new AadharCenterRegister()));

        AadharCenterRegister aadharCenterRegister1 = new AadharCenterRegister();
        aadharCenterRegister1.setCenterName("Center Name");
        assertSame(aadharCenterRegister, aadharCenterServiceImp.update("42", aadharCenterRegister1,
                new MockMultipartFile("Name", new ByteArrayInputStream("AAAAAAAA".getBytes("UTF-8")))));
        verify(aadharCenterRepo).save((AadharCenterRegister) any());
        verify(aadharCenterRepo, atLeast(1)).findById((String) any());
    }

    @Test
    void testUpdate4() throws BusinessException, IOException {
        AadharCenterRegister aadharCenterRegister = mock(AadharCenterRegister.class);
        doThrow(new BusinessException("An error occurred", "An error occurred")).when(aadharCenterRegister)
                .setAddress((String) any());
        doThrow(new BusinessException("An error occurred", "An error occurred")).when(aadharCenterRegister)
                .setAmenities((List<String>) any());
        doThrow(new BusinessException("An error occurred", "An error occurred")).when(aadharCenterRegister)
                .setCenterDescription((String) any());
        doThrow(new BusinessException("An error occurred", "An error occurred")).when(aadharCenterRegister)
                .setCenterName((String) any());
        doThrow(new BusinessException("An error occurred", "An error occurred")).when(aadharCenterRegister)
                .setCity((String) any());
        doThrow(new BusinessException("An error occurred", "An error occurred")).when(aadharCenterRegister)
                .setClosingTime((String) any());
        doThrow(new BusinessException("An error occurred", "An error occurred")).when(aadharCenterRegister)
                .setContactInfo((String) any());
        doThrow(new BusinessException("An error occurred", "An error occurred")).when(aadharCenterRegister)
                .setLocationPin(anyLong());
        doThrow(new BusinessException("An error occurred", "An error occurred")).when(aadharCenterRegister)
                .setOpeningTime((String) any());
        doThrow(new BusinessException("An error occurred", "An error occurred")).when(aadharCenterRegister)
                .setPlacesNearBy((List<String>) any());
        doThrow(new BusinessException("An error occurred", "An error occurred")).when(aadharCenterRegister)
                .setPostedDate((Date) any());
        doThrow(new BusinessException("An error occurred", "An error occurred")).when(aadharCenterRegister)
                .setState((String) any());
        doThrow(new BusinessException("An error occurred", "An error occurred")).when(aadharCenterRegister)
                .setTransportFacilities((List<String>) any());
        doThrow(new BusinessException("An error occurred", "An error occurred")).when(aadharCenterRegister)
                .setVisualsOfCenter((byte[]) any());
        Optional<AadharCenterRegister> ofResult = Optional.of(aadharCenterRegister);
        when(aadharCenterRepo.save((AadharCenterRegister) any())).thenReturn(new AadharCenterRegister());
        when(aadharCenterRepo.findById((String) any())).thenReturn(ofResult);

        AadharCenterRegister aadharCenterRegister1 = new AadharCenterRegister();
        aadharCenterRegister1.setCenterName("Center Name");
        assertThrows(BusinessException.class, () -> aadharCenterServiceImp.update("42", aadharCenterRegister1,
                new MockMultipartFile("Name", new ByteArrayInputStream("AAAAAAAA".getBytes("UTF-8")))));
        verify(aadharCenterRepo, atLeast(1)).findById((String) any());
        verify(aadharCenterRegister).setVisualsOfCenter((byte[]) any());
    }

    @Test
    void testUpdate5() throws BusinessException, IOException {
        AadharCenterRegister aadharCenterRegister = mock(AadharCenterRegister.class);
        doThrow(new BusinessException("An error occurred", "An error occurred")).when(aadharCenterRegister)
                .setAddress((String) any());
        doThrow(new BusinessException("An error occurred", "An error occurred")).when(aadharCenterRegister)
                .setAmenities((List<String>) any());
        doThrow(new BusinessException("An error occurred", "An error occurred")).when(aadharCenterRegister)
                .setCenterDescription((String) any());
        doThrow(new BusinessException("An error occurred", "An error occurred")).when(aadharCenterRegister)
                .setCenterName((String) any());
        doThrow(new BusinessException("An error occurred", "An error occurred")).when(aadharCenterRegister)
                .setCity((String) any());
        doThrow(new BusinessException("An error occurred", "An error occurred")).when(aadharCenterRegister)
                .setClosingTime((String) any());
        doThrow(new BusinessException("An error occurred", "An error occurred")).when(aadharCenterRegister)
                .setContactInfo((String) any());
        doThrow(new BusinessException("An error occurred", "An error occurred")).when(aadharCenterRegister)
                .setLocationPin(anyLong());
        doThrow(new BusinessException("An error occurred", "An error occurred")).when(aadharCenterRegister)
                .setOpeningTime((String) any());
        doThrow(new BusinessException("An error occurred", "An error occurred")).when(aadharCenterRegister)
                .setPlacesNearBy((List<String>) any());
        doThrow(new BusinessException("An error occurred", "An error occurred")).when(aadharCenterRegister)
                .setPostedDate((Date) any());
        doThrow(new BusinessException("An error occurred", "An error occurred")).when(aadharCenterRegister)
                .setState((String) any());
        doThrow(new BusinessException("An error occurred", "An error occurred")).when(aadharCenterRegister)
                .setTransportFacilities((List<String>) any());
        doThrow(new BusinessException("An error occurred", "An error occurred")).when(aadharCenterRegister)
                .setVisualsOfCenter((byte[]) any());
        Optional<AadharCenterRegister> ofResult = Optional.of(aadharCenterRegister);
        when(aadharCenterRepo.save((AadharCenterRegister) any())).thenReturn(new AadharCenterRegister());
        when(aadharCenterRepo.findById((String) any())).thenReturn(ofResult);

        AadharCenterRegister aadharCenterRegister1 = new AadharCenterRegister();
        aadharCenterRegister1.setCenterName("");
        assertThrows(BusinessException.class, () -> aadharCenterServiceImp.update("42", aadharCenterRegister1,
                new MockMultipartFile("Name", new ByteArrayInputStream("AAAAAAAA".getBytes("UTF-8")))));
        verify(aadharCenterRepo, atLeast(1)).findById((String) any());
    }

    @Test
    void testDeleteById() {
        doNothing().when(aadharCenterRepo).deleteById((String) any());
        when(aadharCenterRepo.findById((String) any())).thenReturn(Optional.of(new AadharCenterRegister()));
        assertTrue(aadharCenterServiceImp.deleteById("42"));
        verify(aadharCenterRepo).findById((String) any());
        verify(aadharCenterRepo).deleteById((String) any());
    }

    @Test
    void testDeleteById2() {
        doThrow(new BusinessException("An error occurred", "An error occurred")).when(aadharCenterRepo)
                .deleteById((String) any());
        when(aadharCenterRepo.findById((String) any())).thenReturn(Optional.of(new AadharCenterRegister()));
        assertThrows(BusinessException.class, () -> aadharCenterServiceImp.deleteById("42"));
        verify(aadharCenterRepo).findById((String) any());
        verify(aadharCenterRepo).deleteById((String) any());
    }

    @Test
    void testDeleteById3() {
        doNothing().when(aadharCenterRepo).deleteById((String) any());
        when(aadharCenterRepo.findById((String) any())).thenReturn(Optional.empty());
        assertThrows(BusinessException.class, () -> aadharCenterServiceImp.deleteById("42"));
        verify(aadharCenterRepo).findById((String) any());
    }

    @Test
    void testGetAllCenter() {
        when(aadharCenterRepo.findAll()).thenReturn(new ArrayList<>());
        assertThrows(BusinessException.class, () -> aadharCenterServiceImp.getAllCenter());
        verify(aadharCenterRepo).findAll();
    }

    @Test
    void testGetAllCenter2() {
        ArrayList<AadharCenterRegister> aadharCenterRegisterList = new ArrayList<>();
        aadharCenterRegisterList.add(new AadharCenterRegister());
        when(aadharCenterRepo.findAll()).thenReturn(aadharCenterRegisterList);
        List<AadharCenterRegister> actualAllCenter = aadharCenterServiceImp.getAllCenter();
        assertSame(aadharCenterRegisterList, actualAllCenter);
        assertEquals(1, actualAllCenter.size());
        verify(aadharCenterRepo).findAll();
    }

    @Test
    void testGetAllCenter3() {
        when(aadharCenterRepo.findAll()).thenThrow(new BusinessException("An error occurred", "An error occurred"));
        assertThrows(BusinessException.class, () -> aadharCenterServiceImp.getAllCenter());
        verify(aadharCenterRepo).findAll();
    }

    @Test
    void testGetCenterByLocationPin() {
        when(aadharCenterRepo.findBylocationPin(anyLong())).thenReturn(new ArrayList<>());
        assertThrows(BusinessException.class, () -> aadharCenterServiceImp.getCenterByLocationPin(1L));
        verify(aadharCenterRepo).findBylocationPin(anyLong());
    }

    @Test
    void testGetCenterByLocationPin2() {
        ArrayList<AadharCenterRegister> aadharCenterRegisterList = new ArrayList<>();
        aadharCenterRegisterList.add(new AadharCenterRegister());
        when(aadharCenterRepo.findBylocationPin(anyLong())).thenReturn(aadharCenterRegisterList);
        List<AadharCenterRegister> actualCenterByLocationPin = aadharCenterServiceImp.getCenterByLocationPin(1L);
        assertSame(aadharCenterRegisterList, actualCenterByLocationPin);
        assertEquals(1, actualCenterByLocationPin.size());
        verify(aadharCenterRepo).findBylocationPin(anyLong());
    }

    @Test
    void testGetCenterByLocationPin3() {
        when(aadharCenterRepo.findBylocationPin(anyLong()))
                .thenThrow(new BusinessException("An error occurred", "An error occurred"));
        assertThrows(BusinessException.class, () -> aadharCenterServiceImp.getCenterByLocationPin(1L));
        verify(aadharCenterRepo).findBylocationPin(anyLong());
    }

    @Test
    void testGetCenterById() {
        AadharCenterRegister aadharCenterRegister = new AadharCenterRegister();
        when(aadharCenterRepo.findById((String) any())).thenReturn(Optional.of(aadharCenterRegister));
        assertSame(aadharCenterRegister, aadharCenterServiceImp.getCenterById("42"));
        verify(aadharCenterRepo).findById((String) any());
    }

    @Test
    void testGetCenterById2() {
        when(aadharCenterRepo.findById((String) any())).thenReturn(Optional.empty());
        assertThrows(BusinessException.class, () -> aadharCenterServiceImp.getCenterById("42"));
        verify(aadharCenterRepo).findById((String) any());
    }

    @Test
    void testGetCenterById3() {
        when(aadharCenterRepo.findById((String) any()))
                .thenThrow(new BusinessException("An error occurred", "An error occurred"));
        assertThrows(BusinessException.class, () -> aadharCenterServiceImp.getCenterById("42"));
        verify(aadharCenterRepo).findById((String) any());
    }

    @Test
    void testGetCenterByCity() {
        when(aadharCenterRepo.findByCity((String) any())).thenReturn(new ArrayList<>());
        assertThrows(BusinessException.class, () -> aadharCenterServiceImp.getCenterByCity("Oxford"));
        verify(aadharCenterRepo).findByCity((String) any());
    }

    @Test
    void testGetCenterByCity2() {
        ArrayList<AadharCenterRegister> aadharCenterRegisterList = new ArrayList<>();
        aadharCenterRegisterList.add(new AadharCenterRegister());
        when(aadharCenterRepo.findByCity((String) any())).thenReturn(aadharCenterRegisterList);
        List<AadharCenterRegister> actualCenterByCity = aadharCenterServiceImp.getCenterByCity("Oxford");
        assertSame(aadharCenterRegisterList, actualCenterByCity);
        assertEquals(1, actualCenterByCity.size());
        verify(aadharCenterRepo).findByCity((String) any());
    }

    @Test
    void testGetCenterByCity3() {
        when(aadharCenterRepo.findByCity((String) any()))
                .thenThrow(new BusinessException("An error occurred", "An error occurred"));
        assertThrows(BusinessException.class, () -> aadharCenterServiceImp.getCenterByCity("Oxford"));
        verify(aadharCenterRepo).findByCity((String) any());
    }

    @Test
    void testCreateAppointment2() throws UnsupportedEncodingException {
        when(aadharCenterRepo.save((AadharCenterRegister) any())).thenReturn(new AadharCenterRegister());
        ArrayList<String> amenities = new ArrayList<>();
        byte[] visualsOfCenter = "AAAAAAAA".getBytes("UTF-8");
        ArrayList<String> placesNearBy = new ArrayList<>();
        ArrayList<String> transportFacilities = new ArrayList<>();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date postedDate = Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant());
        when(aadharCenterRepo.findById((String) any())).thenReturn(Optional.of(new AadharCenterRegister("42",
                "Center Name", "Opening Time", "Closing Time", amenities, "42 Main St", "Oxford", "MD", 1L, visualsOfCenter,
                "Center Description", "Contact Info", placesNearBy, transportFacilities, postedDate, new ArrayList<>())));
        Appointment appointment = new Appointment("42", "Appointment Start Time", "Appointment End Time",
                AppointmentStatus.Available, "2020-03-01");

        assertSame(appointment, aadharCenterServiceImp.createAppointment("42", appointment));
        verify(aadharCenterRepo).save((AadharCenterRegister) any());
        verify(aadharCenterRepo, atLeast(1)).findById((String) any());
    }

    @Test
    void testCreateAppointment3() {
        AadharCenterRegister aadharCenterRegister = mock(AadharCenterRegister.class);
        when(aadharCenterRegister.getSlots()).thenThrow(new BusinessException("An error occurred", "An error occurred"));
        doThrow(new BusinessException("An error occurred", "An error occurred")).when(aadharCenterRegister)
                .setSlots((List<Appointment>) any());
        Optional<AadharCenterRegister> ofResult = Optional.of(aadharCenterRegister);
        when(aadharCenterRepo.save((AadharCenterRegister) any())).thenReturn(new AadharCenterRegister());
        when(aadharCenterRepo.findById((String) any())).thenReturn(ofResult);
        assertThrows(BusinessException.class, () -> aadharCenterServiceImp.createAppointment("42", new Appointment("42",
                "Appointment Start Time", "Appointment End Time", AppointmentStatus.Available, "2020-03-01")));
        verify(aadharCenterRepo, atLeast(1)).findById((String) any());
        verify(aadharCenterRegister).getSlots();
    }



    /**
     * Method under test: {@link AadharCenterServiceImp#deleteAppointment(String)}
     */
    @Test
    void testDeleteAppointment2() {
        AadharCenterRegister aadharCenterRegister = new AadharCenterRegister();
        aadharCenterRegister.setSlots(new ArrayList<>());
        Optional<AadharCenterRegister> ofResult = Optional.of(aadharCenterRegister);
        when(aadharCenterRepo.save((AadharCenterRegister) any())).thenReturn(new AadharCenterRegister());
        when(aadharCenterRepo.findById((String) any())).thenReturn(ofResult);
        assertTrue(aadharCenterServiceImp.deleteAppointment("42"));
        verify(aadharCenterRepo).save((AadharCenterRegister) any());
        verify(aadharCenterRepo, atLeast(1)).findById((String) any());
    }


    /**
     * Method under test: {@link AadharCenterServiceImp#deleteAppointment(String)}
     */
    @Test
    void testDeleteAppointment4() {
        AadharCenterRegister aadharCenterRegister = new AadharCenterRegister();
        aadharCenterRegister.setSlots(new ArrayList<>());
        Optional<AadharCenterRegister> ofResult = Optional.of(aadharCenterRegister);
        when(aadharCenterRepo.save((AadharCenterRegister) any()))
                .thenThrow(new BusinessException("An error occurred", "An error occurred"));
        when(aadharCenterRepo.findById((String) any())).thenReturn(ofResult);
        assertThrows(BusinessException.class, () -> aadharCenterServiceImp.deleteAppointment("42"));
        verify(aadharCenterRepo).save((AadharCenterRegister) any());
        verify(aadharCenterRepo, atLeast(1)).findById((String) any());
    }


    /**
     * Method under test: {@link AadharCenterServiceImp#getAllSlots(String)}
     */
    @Test
    void testGetAllSlots2() {
        AadharCenterRegister aadharCenterRegister = new AadharCenterRegister();
        aadharCenterRegister.setSlots(new ArrayList<>());
        Optional<AadharCenterRegister> ofResult = Optional.of(aadharCenterRegister);
        when(aadharCenterRepo.findById((String) any())).thenReturn(ofResult);
        assertThrows(BusinessException.class, () -> aadharCenterServiceImp.getAllSlots("42"));
        verify(aadharCenterRepo).findById((String) any());
    }

    /**
     * Method under test: {@link AadharCenterServiceImp#getAllSlots(String)}
     */
    @Test
    void testGetAllSlots3() {
        AadharCenterRegister aadharCenterRegister = mock(AadharCenterRegister.class);
        when(aadharCenterRegister.getSlots()).thenThrow(new BusinessException("An error occurred", "An error occurred"));
        Optional<AadharCenterRegister> ofResult = Optional.of(aadharCenterRegister);
        when(aadharCenterRepo.findById((String) any())).thenReturn(ofResult);
        assertThrows(BusinessException.class, () -> aadharCenterServiceImp.getAllSlots("42"));
        verify(aadharCenterRepo).findById((String) any());
        verify(aadharCenterRegister).getSlots();
    }

}

