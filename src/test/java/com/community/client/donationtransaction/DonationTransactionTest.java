package com.community.client.donationtransaction;

import com.community.client.models.DonationTransaction;
import com.community.client.models.Project;
import com.community.client.repositories.DonationTransactionRepository;
import com.community.client.repositories.ProjectRepository;
import com.community.client.services.DonationTransactionService;
import com.community.client.services.ProjectService;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
public class DonationTransactionTest {
    // Running a 'Mock Test' to save donation transaction through its functionality using a mock donation transaction repository, service, and model
    @Test
    public void testCheckToSaveDonationTransactionFunctionality(){
        //Creating a mock donation transaction repository
        DonationTransactionRepository mockDonationTransactionRepository = mock(DonationTransactionRepository.class);
        //Create a mock donation transaction service
        DonationTransactionService mockDonationTransactionService = new DonationTransactionService(mockDonationTransactionRepository);

        //creating a dummy user
        DonationTransaction mockDonationTransaction = new DonationTransaction();
        mockDonationTransaction.setId(1L);
        mockDonationTransaction.setDonorName("test");
        mockDonationTransaction.setDonorEmail("test@gmail.com");
        mockDonationTransaction.setAmount("756");
        mockDonationTransaction.setCreditCard("2843948394839483");
        mockDonationTransaction.setDate("2022-01-26");
        mockDonationTransaction.setProjectId("1");
        mockDonationTransaction.setUserId("4");
        mockDonationTransaction.setCardCvc("435");
        mockDonationTransaction.setCardYear("2027");
        mockDonationTransaction.setCardMonth("11");

        //Save method by mocking the Donation Transaction repository using mockito function
        when(mockDonationTransactionRepository.save(mockDonationTransaction)).thenReturn(mockDonationTransaction);

        // Storing that donation transaction to the mockDonationTransactionRepository
        DonationTransaction savedDonationTransaction = mockDonationTransactionService.saveTransaction(mockDonationTransaction);

        // Make assertion
        assertEquals(1L, savedDonationTransaction.getId());
        assertEquals("test", savedDonationTransaction.getDonorName());
        assertEquals("test@gmail.com", savedDonationTransaction.getDonorEmail());
        assertEquals("756", savedDonationTransaction.getAmount());
        assertEquals("2843948394839483", savedDonationTransaction.getCreditCard());
        assertEquals("2022-01-26", savedDonationTransaction.getDate());
        assertEquals("1", savedDonationTransaction.getProjectId());
        assertEquals("4", savedDonationTransaction.getUserId());
        assertEquals("435", savedDonationTransaction.getCardCvc());
        assertEquals("2027", savedDonationTransaction.getCardYear());
        assertEquals("11", savedDonationTransaction.getCardMonth());
    }

    //Test to get one project by ID
    @Test
    public void testToGetProjectById(){
        //Creating a mock donation transaction and project repository
        DonationTransactionRepository mockDonationTransactionRepository = mock(DonationTransactionRepository.class);
        ProjectRepository mockProjectRepository = mock(ProjectRepository.class);
        //Create a mock donation transaction and project service
        DonationTransactionService mockDonationTransactionService = new DonationTransactionService(mockDonationTransactionRepository);
        ProjectService mockProjectService = new ProjectService(mockProjectRepository);

        //creating a dummy user
        // for project
        Project mockProject = new Project (1L);
        // for donation transaction
        DonationTransaction mockDonationTransaction = new DonationTransaction();
        mockDonationTransaction.setId(1L);
        mockDonationTransaction.setDonorName("test");
        mockDonationTransaction.setDonorEmail("test@gmail.com");
        mockDonationTransaction.setAmount("756");
        mockDonationTransaction.setCreditCard("2843948394839483");
        mockDonationTransaction.setDate("2022-01-26");
        mockDonationTransaction.setProjectId("1");
        mockDonationTransaction.setUserId("4");
        mockDonationTransaction.setCardCvc("435");
        mockDonationTransaction.setCardYear("2027");
        mockDonationTransaction.setCardMonth("11");

        //Mock the repository save method
        when(mockProjectRepository.findProjectById(1L)).thenReturn(java.util.Optional.of(mockProject));

        // Save that project to the mockProjectRepository
        mockProjectService.saveProject(mockProject);

        //Get the project by ID
        Project fetchProject = mockProjectService.getProjectById(1L);

        // Make assertion
        //assertEquals("1", fetchProject.getProjectId().getId());

    }
}
