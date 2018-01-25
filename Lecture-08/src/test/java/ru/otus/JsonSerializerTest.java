package ru.otus;

import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import ru.otus.model.CollectionsWine;
import ru.otus.model.EmptyWine;
import ru.otus.model.PrimitiveWine;
import ru.otus.model.Wine;

import java.util.ArrayList;
import static org.junit.Assert.*;

public class JsonSerializerTest {
    private JsonSerializer jsonSerializer;

    @Before
    public void initialize() {
        jsonSerializer = new JsonSerializer();
    }

    @Test
    public void convertEmptyClassToJsonString() {
        EmptyWine emptyWine = new EmptyWine();
        jsonSerializer.objectToJson(emptyWine);

        Gson gson = new Gson();
        String json = gson.toJson(emptyWine);

        assertEquals(json, jsonSerializer.getJsonString());
    }

    @Test
    public void convertClassPrimitivesToJsonString() {
        PrimitiveWine primitiveWine = new PrimitiveWine();
        primitiveWine.setPrice(145.53);
        primitiveWine.setStrength(12.5f);
        primitiveWine.setBalance(10);
        primitiveWine.setAvailable(true);

        Gson gson = new Gson();
        String json = gson.toJson(primitiveWine);
        jsonSerializer.objectToJson(primitiveWine);
        assertEquals(json, jsonSerializer.getJsonString());
    }

    @Test
    public void convertClassStringToJsonString() {
        Wine wine = new Wine();
        wine.setName("Водка Пять Озер");
        wine.setRatings(new Object[]{'c', "A", 5});

        Gson gson = new Gson();
        String json = gson.toJson(wine);
        jsonSerializer.objectToJson(wine);
        assertEquals(json, jsonSerializer.getJsonString());
    }

    @Test
    public void convertClassNestedObjectsToJsonString() {
        PrimitiveWine primitiveWine = new PrimitiveWine();
        Wine wine = new Wine();
        wine.setName("Whisky");
        primitiveWine.setWine(wine);

        Gson gson = new Gson();
        String json = gson.toJson(primitiveWine);
        jsonSerializer.objectToJson(primitiveWine);
        assertEquals(json, jsonSerializer.getJsonString());
    }

    @Test
    public void convertClassArrayNestedObjectsToJsonString() {
        PrimitiveWine primitiveWine = new PrimitiveWine();

        Wine wine = new Wine();
        wine.setName("Whisky");
        primitiveWine.setWine(wine);

        int size = 2;
        Wine[] wines = new Wine[size];
        for (int i = 0 ; i < size; i++) {
            Wine wine1 = new Wine();
            wine1.setName("some wine - " + i);
            wines[i] = wine1;
        }
        primitiveWine.setWines(wines);

        Gson gson = new Gson();
        String json = gson.toJson(primitiveWine);
        jsonSerializer.objectToJson(primitiveWine);
        assertEquals(json, jsonSerializer.getJsonString());
    }

    @Test
    public void convertClassArraysOfPrimitivesToJsonString() {
        PrimitiveWine primitiveWine = new PrimitiveWine();
        primitiveWine.setCapacity(new int[] {1,2,3});

        Gson gson = new Gson();
        String json = gson.toJson(primitiveWine);
        jsonSerializer.objectToJson(primitiveWine);
        assertEquals(json, jsonSerializer.getJsonString());
    }

    @Test
    public void convertClassArraysOfStringsToJsonString() {
        PrimitiveWine primitiveWine = new PrimitiveWine();
        primitiveWine.setGrapes(new String[] {"Мерло","Корвина","Рондинелла"});

        Gson gson = new Gson();
        String json = gson.toJson(primitiveWine);
        jsonSerializer.objectToJson(primitiveWine);
        assertEquals(json, jsonSerializer.getJsonString());
    }

    @Test
    public void convertClassListOfObjectsToJSonString() {
        CollectionsWine collectionsWine = new CollectionsWine();

        Wine wineBeer = new Wine();
        wineBeer.setName("Пиво");
        wineBeer.setRatings(new Object[]{15,20});

        Wine wineWine = new Wine();
        wineWine.setName("Вино");

        ArrayList<Wine> grapeList = new ArrayList<>();
        grapeList.add(wineBeer);
        grapeList.add(wineWine);

        collectionsWine.setGrapes(grapeList);

        Gson gson = new Gson();
        String json = gson.toJson(collectionsWine);
        jsonSerializer.objectToJson(collectionsWine);
        assertEquals(json, jsonSerializer.getJsonString());
    }
}