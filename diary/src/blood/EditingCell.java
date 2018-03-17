/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//https://gist.github.com/abhinayagarwal/9383881 accessed 10/3/18

package blood;

import java.util.ArrayList;
import java.util.List;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 *
 * @author amydo
 */
class EditingCell extends TableCell<blood, String> 
{
    //The textfield that goes in when you are on that row 
    private TextField textField;

    //constructor 
    public EditingCell() {}    

    //method called when you click into the box to beging edttiting 
    @Override
    public void startEdit() 
    {
	if (!isEmpty()) 
        {
            super.startEdit(); //Method from TableCell class
            if (textField == null) 
            {
                createTextField();
            }

            //Dispaly the text field in the table view 
            setGraphic(textField);
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
               
            //Focus on the textfield so that you can type in it 
            textField.requestFocus();
            textField.selectAll();

	}
    }

    
    //Method called when you click off of that cell
    @Override
    public void cancelEdit() 
    {
        super.cancelEdit();//Method from TableCell class

        //Sets the text in the table to what you have entered and removed the textfield 
        setText((String) getItem());
        setGraphic(null);
    }

    
    @Override
    public void updateItem(String item, boolean empty) 
    {
        super.updateItem(item, empty);

        if (empty) 
        {
            setText(null);
            setGraphic(null);
        } 
        else 
        {
            if (isEditing()) 
            {
                if (textField != null) 
                {
                    textField.setText(getString());
                }
                setGraphic(textField);
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            } 
            else 
            {
                setText(getString());
                setContentDisplay(ContentDisplay.TEXT_ONLY);
            }
        }
    }

    //Method to create the textfield which then appears in the table 
    private void createTextField() 
    {
        //Fills the textfiled with the data that is already in the cell
        textField = new TextField(getString());
       
        //method when you click into the text box
        textField.focusedProperty().addListener(new ChangeListener<Boolean>() 
        {
            @Override //storess the changes you make to the textfield (runs the commit method)
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) 
            {
                if (!arg2) 
                {
                    commitEdit(textField.getText());
                }
            }
        });

        //method for when different keys are pressed 
        textField.setOnKeyPressed(new EventHandler<KeyEvent>() 
        {
            @Override
            public void handle(KeyEvent t) 
            {
                if (t.getCode() == KeyCode.ENTER) 
                {
                    commitEdit(textField.getText());
                } 
                else if (t.getCode() == KeyCode.ESCAPE) 
                {
                    cancelEdit();
                } 
                else if (t.getCode() == KeyCode.TAB) //commits the edit and then moves to the next column
                {
                    commitEdit(textField.getText());
                    TableColumn nextColumn = getNextColumn(!t.isShiftDown());
                    
                    if (nextColumn != null) 
                    {
                        getTableView().edit(getTableRow().getIndex(),nextColumn);
                    }

                }
            }

        });
    }

    //Gets the text from the cell 
    private String getString() 
    {
        return getItem() == null ? "" : getItem().toString();
    }

    
    //Method to get the next column 
    private TableColumn<blood, ?> getNextColumn(boolean forward) 
    {
        //An arraylist of all of the columns in the table 
        List<TableColumn<blood, ?>> columns = new ArrayList<>();
        for (TableColumn<blood, ?> column : getTableView().getColumns()) 
        {
            columns.addAll(getLeaves(column));
        }
        
        // There is no other column that supports editing.
        if (columns.size() < 2) 
        {
            return null;
        }
        
        int currentIndex = columns.indexOf(getTableColumn());
        int nextIndex = currentIndex;
        if (forward) 
        {
            nextIndex++;
            if (nextIndex > columns.size() - 1) 
            {
                nextIndex = 0;
            }
        } 
        else 
        {
            nextIndex--;
            if (nextIndex < 0) 
            {
                nextIndex = columns.size() - 1;
            }
        }
        return columns.get(nextIndex);
    }

    private List<TableColumn<blood, ?>> getLeaves(TableColumn<blood, ?> root) 
    {
        List<TableColumn<blood, ?>> columns = new ArrayList<>();
        if (root.getColumns().isEmpty()) 
        {
            // We only want the leaves that are editable.
            if (root.isEditable()) 
            {
                columns.add(root);
            }
            return columns;
        } 
        else 
        {
            for (TableColumn<blood, ?> column : root.getColumns()) 
            {
                columns.addAll(getLeaves(column));
            }
            return columns;
        }
    }
}
