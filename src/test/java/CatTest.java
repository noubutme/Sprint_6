import com.example.Cat;
import com.example.Feline;
import com.example.Predator;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.mockito.Mockito.spy;

@RunWith(MockitoJUnitRunner.Silent.class)
public class CatTest {

    @Spy
    Feline feline;

    @Test
    public void getSoundReturnsCorrectValue(){
        Cat cat = spy(new Cat(feline));
        String expectedSound = "Мяу";

        String actualSound = cat.getSound();

        Assert.assertEquals("Method getSound return incorrect value.", expectedSound, actualSound );
    }
    @Mock
    Predator predator;

    @Test
    public void getFoodReturnCorrectList() throws Exception {
        Cat cat = spy(new Cat(feline));
        Mockito.when(predator.eatMeat()).thenReturn(List.of("Животные", "Птицы", "Рыба"));
        List<String> expectedFood = List.of("Животные", "Птицы", "Рыба");

        List<String> actualFood = cat.getFood();

        Assert.assertEquals("Method getFood return incorrect value.", expectedFood, actualFood);
    }

}