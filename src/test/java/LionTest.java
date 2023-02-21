import com.example.Feline;
import com.example.Lion;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.spy;

@RunWith(Parameterized.class)
public class LionTest {

    private final String sex;
    private final boolean expectedHasMane;

    @Mock
    Feline feline;

    public LionTest(String sex, boolean expectedHasMane) {
        this.sex = sex;
        this.expectedHasMane = expectedHasMane;
    }

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Parameterized.Parameters
    public static Object[][] getTypeOfFoodForAnimal() {
        return new Object[][] {
                { "Самец", true},
                { "Самка", false},
        };
    }

    @Test
    public void lionConstructionCreateLion() throws Exception {
        Lion lion = spy(new Lion(sex, feline));
        Mockito.when(lion.doesHaveMane()).thenReturn(expectedHasMane);

        boolean actualHasMane = lion.doesHaveMane();

        Assert.assertEquals("Construction doesn't create lion.", expectedHasMane, actualHasMane);
    }

    @Test
    public void getLionConstructionException() {
        String expectedText = "Используйте допустимые значения пола животного - самец или самка";
        Exception exception = null;

        try {
            Lion lion = new Lion("Кобель", feline);
        } catch (Exception ex){
            exception = ex;
        }

        Assert.assertNotNull(exception);
        Assert.assertEquals("Massage exception is incorrect.", expectedText, exception.getMessage());
    }

    @Test
    public void doesHaveManeReturnCorrectValue() throws Exception {
        Lion lion = spy(new Lion(sex, feline));

        boolean actualMane = lion.doesHaveMane();

        Mockito.verify(lion).doesHaveMane();
        Assert.assertEquals("Method doesHaveMane return incorrect value.", expectedHasMane, actualMane);
    }

    @Test
    public void getKittensReturnCorrectValue() throws Exception {
        Lion lion = new Lion(sex, feline);
        int expectedKittens = 1;
        Mockito.when(feline.getKittens()).thenReturn(1);

        int actualKittens = lion.getKittens();

        Mockito.verify(feline).getKittens();
        Assert.assertEquals("Method getKittens return incorrect value.", expectedKittens, actualKittens);
    }

    @Test
    public void getFoodReturnCorrectValue() throws Exception {
        Lion lion = new Lion(sex, feline);
        Mockito.when(feline.getFood("Хищник")).thenReturn(List.of("Животные", "Птицы", "Рыба"));
        List<String> expectedListOfMeat = List.of("Животные", "Птицы", "Рыба");

        List<String> aclualListOfMeat = lion.getFood();

        Mockito.verify(feline).getFood("Хищник");
        Assert.assertEquals("Method getFood return incorrect value.",expectedListOfMeat, aclualListOfMeat);
    }

}