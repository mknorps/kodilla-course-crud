package com.crud.tasks.trello.mapper;

import com.crud.tasks.domain.*;
import com.crud.tasks.trello.mappper.TrelloMappper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class TrelloMapperTestSuite {
    @InjectMocks
    private TrelloMappper trelloMappper;

    @Test
    public void testMapToListDto() {
        //Given
        TrelloList trelloList1 = new TrelloList("1", "test list", false);
        TrelloList trelloList2 = new TrelloList("2", "test list 2", true);
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(trelloList1);
        trelloLists.add(trelloList2);
        //When
        List<TrelloListDto> trelloListDto = trelloMappper.mapToListDto(trelloLists);
        trelloLists.remove(trelloList1);
        //Then
        assertEquals(2, trelloListDto.size());
        assertEquals("1", trelloListDto.get(0).getId());
        assertEquals("test list", trelloListDto.get(0).getName());
        assertEquals(false, trelloListDto.get(0).isClosed());

    }

    @Test
    public void testMapToList() {
        //Given
        TrelloListDto trelloListDto1 = new TrelloListDto("1", "test list", false);
        TrelloListDto trelloListDto2 = new TrelloListDto("2", "test list 2", true);
        List<TrelloListDto> trelloListsDto = new ArrayList<>();
        trelloListsDto.add(trelloListDto1);
        trelloListsDto.add(trelloListDto2);
        //When
        List<TrelloList> trelloLists = trelloMappper.mapToList(trelloListsDto);
        trelloListsDto.remove(trelloListDto1);
        //Then
        assertEquals(2, trelloLists.size());
        assertEquals("1", trelloLists.get(0).getId());
        assertEquals("test list", trelloLists.get(0).getName());
        assertEquals(false, trelloLists.get(0).isClosed());
    }

    @Test
    public void testMapToBoards() {
        //Given
        TrelloListDto trelloListDto1 = new TrelloListDto("1", "test list", false);
        TrelloListDto trelloListDto2 = new TrelloListDto("2", "test list 2", true);
        List<TrelloListDto> trelloListsDto = new ArrayList<>();
        trelloListsDto.add(trelloListDto1);
        trelloListsDto.add(trelloListDto2);
        TrelloBoardDto trelloBoardDto = new TrelloBoardDto("7", "test board", trelloListsDto);
        List<TrelloBoardDto> trelloBoardDtoList = new ArrayList<>();
        trelloBoardDtoList.add(trelloBoardDto);
        //When
        List<TrelloBoard> trelloBoardList = trelloMappper.mapToBoards(trelloBoardDtoList);
        trelloBoardDtoList.remove(trelloBoardDto);
        //Then
        assertEquals(1, trelloBoardList.size());
        assertEquals("7", trelloBoardList.get(0).getId());
        assertEquals("test board", trelloBoardList.get(0).getName());
        assertEquals(2, trelloBoardList.get(0).getLists().size());
        assertEquals("2", trelloBoardList.get(0).getLists().get(1).getId());
    }

    @Test
    public void testMapToBoardsDto() {
        //Given
        TrelloList trelloList1 = new TrelloList("1", "test list", false);
        TrelloList trelloList2 = new TrelloList("2", "test list 2", true);
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(trelloList1);
        trelloLists.add(trelloList2);
        TrelloBoard trelloBoard = new TrelloBoard("7", "test board", trelloLists);
        List<TrelloBoard> trelloBoardLists = new ArrayList<>();
        trelloBoardLists.add(trelloBoard);
        //When
        List<TrelloBoardDto> trelloBoardDtoLists = trelloMappper.mapToBoardsDto(trelloBoardLists);
        trelloBoardLists.remove(trelloBoard);
        //Then
        assertEquals(1, trelloBoardDtoLists.size());
        assertEquals("7", trelloBoardDtoLists.get(0).getId());
        assertEquals("test board", trelloBoardDtoLists.get(0).getName());
        assertEquals(2, trelloBoardDtoLists.get(0).getLists().size());
        assertEquals("2", trelloBoardDtoLists.get(0).getLists().get(1).getId());
    }

    @Test
    public void testMapToTrelloCardDto() {
        //Given
        TrelloCard trelloCard = new TrelloCard("test card", "this is the test card", "top", "5");
        //When
        TrelloCardDto trelloCardDto = trelloMappper.mapToTrelloCardDto(trelloCard);
        //Then
        assertEquals("test card", trelloCardDto.getName());
        assertEquals("this is the test card", trelloCardDto.getDescription());
        assertEquals("top", trelloCardDto.getPos());
        assertEquals("5", trelloCardDto.getListId());

    }

    @Test
    public void testMapToTrelloCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("test card", "this is the test card", "top", "5");
        //When
        TrelloCard trelloCard = trelloMappper.mapToTrelloCard(trelloCardDto);
        //Then
        assertEquals("test card", trelloCard.getName());
        assertEquals("this is the test card", trelloCard.getDescription());
        assertEquals("top", trelloCard.getPos());
        assertEquals("5", trelloCard.getListId());

    }
}
