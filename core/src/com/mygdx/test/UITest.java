package com.mygdx.test;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Array;

import javax.swing.event.ListSelectionListener;
import java.util.ArrayList;
import java.util.zip.ZipFile;

public class UITest extends ApplicationAdapter {
    SpriteBatch batch;
    Texture img;
    Label mLabel;
    Stage mStage;
    TextButton mTextButton;
    BitmapFont font;
    TextButton.TextButtonStyle textButtonStyle;
    TextureAtlas buttonAtlas;
    CheckBox _0CheckBox;
    CheckBox _1CheckBox;
    CheckBox _2CheckBox;
    CheckBox _3CheckBox;
    CheckBox.CheckBoxStyle checkBoxStyle;
    ButtonGroup buttonGroup;
    TextField mTextField;
    TextArea mTextArea;
    List mList;
    List.ListStyle mListStyle;

    public UITest() {
        //mStage = new Stage();
    }

    @Override
    public void create() {
		/*batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		*/

        mStage = new Stage();
        Gdx.input.setInputProcessor(mStage);
        font = new BitmapFont();
        Skin skin = new Skin();
        //buttonAtlas = new TextureAtlas("badlogic.jpg");
        //skin.addRegions(buttonAtlas);
        textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = font;
        img = new Texture("badlogic.jpg");
        skin.add("logo", img);

        mTextButton = new TextButton(null, textButtonStyle);
        mTextButton.setPosition(300f, 50f);
        mTextButton.setSize(56f, 56f);
        mTextButton.addListener(new ChangeListener() {
            int t = 0;

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                t++;
                //String text = (String)mList.getSelected();
                String text = "HHHHH";
                mLabel.setText("Clicked for " + t + " times. Text = " + text);
                //System.out.println("Button Pressed");
            }
        });


        /**********CheckBox***************/
        try{
            ZipFile zip = new ZipFile("/mnt/76A0FD4FA0FD15F9/newWorkspace/LIbGDX/desktop/test/resources.zip");
            Texture T1 = new Texture(new ZipFileHandle(zip, "resources/Checked/checkboxes_checked.jpg"));
            Texture T2 = new Texture(new ZipFileHandle(zip, "resources/Unchecked/checkboxes_notChecked.jpg"));
            skin.add("checked", T1);
            skin.add("notChecked", T2);
        } catch (Exception e) {
            System.out.println("NO ZIP FILE!!!");
        }

        //skin.add("checked", new Texture("/mnt/76A0FD4FA0FD15F9/newWorkspace/LIbGDX/desktop/test/checkboxes_checked.jpg"));
        //skin.add("notChecked", new Texture("/mnt/76A0FD4FA0FD15F9/newWorkspace/LIbGDX/desktop/test/checkboxes_notChecked.jpg"));
        Drawable checked = skin.getDrawable("checked");
        Drawable notChecked = skin.getDrawable("notChecked");
        checkBoxStyle = new CheckBox.CheckBoxStyle();
        checkBoxStyle.checkboxOff = notChecked;
        checkBoxStyle.checkboxOn = checked;
        checkBoxStyle.font = font;
        _0CheckBox = new CheckBox("CheckBox_0", checkBoxStyle);
        _1CheckBox = new CheckBox("CheckBox_1", checkBoxStyle);
        _2CheckBox = new CheckBox("CheckBox_2", checkBoxStyle);
        _3CheckBox = new CheckBox("CheckBox_3", checkBoxStyle);
        _0CheckBox.setPosition(80f, 300f);
        _1CheckBox.setPosition(80f, 220f);
        _2CheckBox.setPosition(80f, 140f);
        _3CheckBox.setPosition(80f, 60f);
        buttonGroup = new ButtonGroup();
        buttonGroup.add(_0CheckBox, _1CheckBox, _2CheckBox, _3CheckBox);
        buttonGroup.setMinCheckCount(0);
        buttonGroup.setMaxCheckCount(3);
        _0CheckBox.setChecked(false);
        _1CheckBox.setChecked(false);
        _2CheckBox.setChecked(false);
        _3CheckBox.setChecked(false);
        mStage.addActor(_0CheckBox);
        mStage.addActor(_1CheckBox);
        mStage.addActor(_2CheckBox);
        mStage.addActor(_3CheckBox);

        /*******Button*******/
        textButtonStyle.down = skin.getDrawable("checked");
        textButtonStyle.up = skin.getDrawable("notChecked");
        mStage.addActor(mTextButton);

        /********Label*******/
        Label.LabelStyle labelStyle = new Label.LabelStyle(font, Color.BLUE);
        mLabel = new Label("something", labelStyle);
        mLabel.setPosition(300f, 150f);
        mStage.addActor(mLabel);

        /******TextField*****/
        skin.add("cursor", new Texture("/mnt/76A0FD4FA0FD15F9/newWorkspace/LIbGDX/desktop/test/cursor.jpg"));
        TextField.TextFieldStyle textFieldStyle = new TextField.TextFieldStyle();
        textFieldStyle.font = font;
        textFieldStyle.fontColor = Color.BLUE;
        textFieldStyle.cursor = skin.getDrawable("cursor");
        mTextField = new TextField("", textFieldStyle);
        mTextField.setMessageText("Hello World");
        mTextField.setPosition(300f, 250f);
        mStage.addActor(mTextField);

        /*****TextArea******/
        mTextArea = new TextArea("", textFieldStyle);
        mTextArea.setPosition(300f, 320f);
        mTextArea.setSize(100f, 65f);
        mTextArea.setMessageText("helloworld");
        mStage.addActor(mTextArea);

        /*****List********/
        mListStyle = new List.ListStyle();
        mListStyle.font = font;
        mListStyle.fontColorSelected = Color.BLUE;
        mListStyle.fontColorUnselected = Color.RED;
        mListStyle.selection = checked;
        mList = new List(mListStyle);
        mList.setPosition(450, 320);
        Array<TextButton> entries = new Array<TextButton>();

        entries.add(new TextButton("Hello_1", textButtonStyle));
        entries.add(new TextButton("Hello_2", textButtonStyle));
        entries.add(new TextButton("Hello_3", textButtonStyle));
        mList.setItems(entries);
        mList.layout();
        mList.setSelectedIndex(1);
        mList.addListener(new ChangeListener() {
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                mList.setSelectedIndex(0);
            }
        });
        mStage.addActor(mList);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		/*batch.begin();
		batch.draw(img, 0, 0);
		batch.draw(mLabel, 0, 0);
		batch.end();*/
        mStage.draw();
    }
}

