import com.example.Animal;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;

@RunWith(Parameterized.class)
public class AnimalTest {

    Animal animal = new Animal();
    private final String typeOfAnimal;
    private final List<String> expectedTypeOfFood;

    public AnimalTest(String typeOfAnimal, List<String> expectedTypeOfFood) {
        this.typeOfAnimal = typeOfAnimal;
        this.expectedTypeOfFood = expectedTypeOfFood;
    }

    @Parameterized.Parameters
    public static Object[][] getTypeOfFoodForAmimal() {
        return new Object[][] {
                { "Травоядное", List.of("Трава", "Различные растения")},
                { "Хищник", List.of("Животные", "Птицы", "Рыба")},
        };
    }


    @Test
    public void getFoodForHerbivoreReturnCorrectValues() throws Exception {
        List<String> actualTypeOfFood = animal.getFood(typeOfAnimal);

        Assert.assertEquals("Method getFood return incorrect value.", expectedTypeOfFood, actualTypeOfFood);
    }

    @Test
    public void getFoodException() {
        String animalKind = "Всеядное";
        String expectedText = "Неизвестный вид животного, используйте значение Травоядное или Хищник";
        Exception exception = null;

        try {
            animal.getFood(animalKind);
        } catch (Exception ex){
            exception = ex;
        }

        Assert.assertNotNull(exception);
        Assert.assertEquals("Massage exception is incorrect.", expectedText, exception.getMessage());
    }

    @Test
    public void getFamilyReturnCorrectValue(){
        String expectedFamily = "Существует несколько семейств: заячьи, беличьи, мышиные, кошачьи, псовые, медвежьи, куньи";

        String actualFamily = animal.getFamily();

        Assert.assertEquals("Method getFamily return incorrect value.", expectedFamily, actualFamily);
    }
}