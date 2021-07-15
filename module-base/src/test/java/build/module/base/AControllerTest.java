package build.module.base;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class AControllerTest {
    private AController aController;

    @BeforeEach
//    @BeforeAll  // execute once for a given test class
    public void setUp() {
        System.out.println("setup started..");
        aController = new AController();
        System.out.println("setup ended..");

    }


    @Test
    @DisplayName("testPrintDisplayName")
    void testPrint() {
        aController.print(null);
    }

    @Test
    @RepeatedTest(3)
    void testRepeatedPrint() {

    }



}