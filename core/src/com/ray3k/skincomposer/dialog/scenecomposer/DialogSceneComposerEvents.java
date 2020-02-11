package com.ray3k.skincomposer.dialog.scenecomposer;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Scaling;
import com.ray3k.skincomposer.Main;
import com.ray3k.skincomposer.data.ColorData;
import com.ray3k.skincomposer.data.DrawableData;
import com.ray3k.skincomposer.data.StyleData;
import com.ray3k.skincomposer.dialog.scenecomposer.undoables.*;

public class DialogSceneComposerEvents {
    public enum WidgetType {
        BUTTON, CHECK_BOX, IMAGE, IMAGE_BUTTON, IMAGE_TEXT_BUTTON, LABEL, LIST, PROGRESS_BAR, SELECT_BOX, SLIDER,
        TEXT_BUTTON, TEXT_FIELD, TEXT_AREA, TOUCH_PAD, CONTAINER, HORIZONTAL_GROUP, SCROLL_PANE, STACK, SPLIT_PANE,
        TABLE, TREE, VERTICAL_GROUP
    }
    
    private DialogSceneComposer dialog;
    
    public DialogSceneComposerEvents() {
        this.dialog = DialogSceneComposer.dialog;
    }
    
    public void menuQuit() {
        dialog.hide();
    }
    
    public void menuRefresh() {
    
    }
    
    public void menuClear() {
        processUndoable(new MenuClearUndoable());
    }
    
    public void menuUndo() {
        dialog.model.undo();
        dialog.updateMenuUndoRedo();
    }
    
    public void menuRedo() {
        dialog.model.redo();
        dialog.updateMenuUndoRedo();
    }
    
    public void menuView(DialogSceneComposer.View view) {
        dialog.view = view;
        dialog.updateMenuView();
    }
    
    public void menuHelp() {
        dialog.showHelpDialog();
    }
    
    public void dialogImportImportTemplate(FileHandle loadFile) {
        Main.main.getProjectData().setLastImportExportPath(loadFile.path());
        DialogSceneComposerModel.loadFromJson(loadFile);
        dialog.model.updatePreview();
        dialog.populatePath();
        dialog.populateProperties();
    }
    
    public void dialogExportSaveTemplate(FileHandle saveFile) {
        Main.main.getProjectData().setLastImportExportPath(saveFile.path());
        DialogSceneComposerModel.saveToJson(saveFile);
    }
    
    public void dialogExportSaveJava(FileHandle saveFile) {
        Main.main.getProjectData().setLastImportExportPath(saveFile.path());
    }
    
    public void dialogExportClipboard() {
    
    }
    
    public void dialogSettingsPackage(String packageName) {
    
    }
    
    public void dialogSettingsClass(String className) {
    
    }
    
    public void dialogSettingsSkinPath(String skinPath) {
    
    }
    
    public void dialogSettingsBackgroundColor(ColorData color) {
    
    }
    
    private void processUndoable(SceneComposerUndoable undoable) {
        dialog.model.undoables.add(undoable);
        dialog.model.redoables.clear();
        undoable.redo();
        dialog.updateMenuUndoRedo();
    }
    
    public void rootAddTable(int columns, int rows) {
        processUndoable(new RootAddTableUndoable(columns, rows));
    }
    
    public void tableName(String name) {
        processUndoable(new TableNameUndoable(name));
    }
    
    public void tableBackground(DrawableData background) {
        processUndoable(new TableBackgroundUndoable(background));
    }
    
    public void tableColor(ColorData color) {
        processUndoable(new TableColorUndoable(color));
    }
    
    public void tablePadding(float paddingLeft, float paddingRight, float paddingTop, float paddingBottom) {
        processUndoable(new TablePaddingUndoable(paddingLeft, paddingRight, paddingTop, paddingBottom));
    }
    
    public void tableAlignment(int alignment) {
        processUndoable(new TableAlignmentUndoable(alignment));
    }
    
    public void tableReset() {
        processUndoable(new TableResetUndoable());
    }
    
    public void tableDelete() {
        processUndoable(new TableDeleteUndoable());
    }
    
    public void cellSetWidget(WidgetType widgetType) {
        processUndoable(new CellSetWidgetUndoable(widgetType));
    }
    
    public void cellAddCellToLeft() {
        processUndoable(new CellAddCellToLeftUndoable());
    }
    
    public void cellAddCellToRight() {
        processUndoable(new CellAddCellToRightUndoable());
    }
    
    public void cellAddRowAbove() {
        processUndoable(new CellAddRowAboveUndoable());
    }
    
    public void cellAddRowBelow() {
        processUndoable(new CellAddRowBelowUndoable());
    }
    
    public void cellPaddingSpacing(float paddingLeft, float paddingRight, float paddingTop, float paddingBottom, float spaceLeft, float spaceRight, float spaceTop, float spaceBottom) {
        processUndoable(new CellPaddingSpacingUndoable(paddingLeft, paddingRight, paddingTop, paddingBottom, spaceLeft, spaceRight, spaceTop, spaceBottom));
    }
    
    public void cellExpandFillGrow(boolean expandX, boolean expandY, boolean fillX, boolean fillY, boolean growX, boolean growY) {
        processUndoable(new CellExpandFillGrowUndoable(expandX, expandY, fillX, fillY, growX, growY));
    }
    
    public void cellAlignment(int alignment) {
        processUndoable(new CellAlignmentUndoable(alignment));
    }
    
    public void cellSize(float minWidth, float minHeight, float maxWidth, float maxHeight, float preferredWidth, float preferredHeight) {
        processUndoable(new CellSizeUndoable(minWidth, minHeight, maxWidth, maxHeight, preferredWidth, preferredHeight));
    }
    
    public void cellUniform(boolean uniformX, boolean uniformY) {
        processUndoable(new CellUniformUndoable(uniformX, uniformY));
    }
    
    public void cellColSpan(int colSpan) {
        processUndoable(new CellColSpanUndoable(colSpan));
    }
    
    public void cellReset() {
        processUndoable(new CellResetUndoable());
    }
    
    public void cellDelete() {
        processUndoable(new CellDeleteUndoable());
    }
    
    public void buttonName(String name) {
        processUndoable(new ButtonNameUndoable(name));
    }
    
    public void buttonStyle(StyleData style) {
        processUndoable(new ButtonStyleUndoable(style));
    }
    
    public void buttonChecked(boolean checked) {
        processUndoable(new ButtonCheckedUndoable(checked));
    }
    
    public void buttonDisabled(boolean disabled) {
        processUndoable(new ButtonDisabledUndoable(disabled));
    }
    
    public void buttonColor(ColorData colorData) {
        processUndoable(new ButtonColorUndoable(colorData));
    }
    
    public void buttonPadding(float paddingLeft, float paddingRight, float paddingTop, float paddingBottom) {
        processUndoable(new ButtonPaddingUndoable(paddingLeft, paddingRight, paddingTop, paddingBottom));
    }
    
    public void buttonReset() {
        processUndoable(new ButtonResetUndoable());
    }
    
    public void buttonDelete() {
        processUndoable(new ButtonDeleteUndoable());
    }
    
    public void imageButtonName(String name) {
        processUndoable(new ImageButtonNameUndoable(name));
    }
    
    public void imageButtonStyle(StyleData style) {
        processUndoable(new ImageButtonStyleUndoable(style));
    }
    
    public void imageButtonChecked(boolean checked) {
        processUndoable(new ImageButtonCheckedUndoable(checked));
    }
    
    public void imageButtonDisabled(boolean disabled) {
        processUndoable(new ImageButtonDisabledUndoable(disabled));
    }
    
    public void imageButtonColor(ColorData colorData) {
        processUndoable(new ImageButtonColorUndoable(colorData));
    }
    
    public void imageButtonPadding(float paddingLeft, float paddingRight, float paddingTop, float paddingBottom) {
        processUndoable(new ImageButtonPaddingUndoable(paddingLeft, paddingRight, paddingTop, paddingBottom));
    }
    
    public void imageButtonReset() {
        processUndoable(new ImageButtonResetUndoable());
    }
    
    public void imageButtonDelete() {
        processUndoable(new ImageButtonDeleteUndoable());
    }
    
    public void imageTextButtonName(String name) {
        processUndoable(new ImageTextButtonNameUndoable(name));
    }
    
    public void imageTextButtonText(String text) {
        processUndoable(new ImageTextButtonTextUndoable(text));
    }
    
    public void imageTextButtonStyle(StyleData style) {
        processUndoable(new ImageTextButtonStyleUndoable(style));
    }
    
    public void imageTextButtonChecked(boolean checked) {
        processUndoable(new ImageTextButtonCheckedUndoable(checked));
    }
    
    public void imageTextButtonDisabled(boolean disabled) {
        processUndoable(new ImageTextButtonDisabledUndoable(disabled));
    }
    
    public void imageTextButtonColor(ColorData colorData) {
        processUndoable(new ImageTextButtonColorUndoable(colorData));
    }
    
    public void imageTextButtonPadding(float paddingLeft, float paddingRight, float paddingTop, float paddingBottom) {
        processUndoable(new ImageTextButtonPaddingUndoable(paddingLeft, paddingRight, paddingTop, paddingBottom));
    }
    
    public void imageTextButtonReset() {
        processUndoable(new ImageTextButtonResetUndoable());
    }
    
    public void imageTextButtonDelete() {
        processUndoable(new ImageTextButtonDeleteUndoable());
    }
    
    public void textButtonName(String name) {
        processUndoable(new TextButtonNameUndoable(name));
    }
    
    public void textButtonText(String text) {
        processUndoable(new TextButtonTextUndoable(text));
    }
    
    public void textButtonStyle(StyleData style) {
        processUndoable(new TextButtonStyleUndoable(style));
    }
    
    public void textButtonChecked(boolean checked) {
        processUndoable(new TextButtonCheckedUndoable(checked));
    }
    
    public void textButtonDisabled(boolean disabled) {
        processUndoable(new TextButtonDisabledUndoable(disabled));
    }
    
    public void textButtonColor(ColorData colorData) {
        processUndoable(new TextButtonColorUndoable(colorData));
    }
    
    public void textButtonPadding(float paddingLeft, float paddingRight, float paddingTop, float paddingBottom) {
        processUndoable(new TextButtonPaddingUndoable(paddingLeft, paddingRight, paddingTop, paddingBottom));
    }
    
    public void textButtonReset() {
        processUndoable(new TextButtonResetUndoable());
    }
    
    public void textButtonDelete() {
        processUndoable(new TextButtonDeleteUndoable());
    }
    
    public void checkBoxChecked(boolean checked) {
        processUndoable(new CheckBoxCheckedUndoable(checked));
    }
    
    public void checkBoxColor(ColorData color) {
        processUndoable(new CheckBoxColorUndoable(color));
    }
    
    public void checkBoxDelete() {
        processUndoable(new CheckBoxDeleteUndoable());
    }
    
    public void checkBoxDisabled(boolean disabled) {
        processUndoable(new CheckBoxDisabledUndoable(disabled));
    }
    
    public void checkBoxName(String name) {
        processUndoable(new CheckBoxNameUndoable(name));
    }
    
    public void checkBoxPadding(float padLeft, float padRight, float padTop, float padBottom) {
        processUndoable(new CheckBoxPaddingUndoable(padLeft, padRight, padTop, padBottom));
    }
    
    public void checkBoxReset() {
        processUndoable(new CheckBoxResetUndoable());
    }
    
    public void checkBoxStyle(StyleData style) {
        processUndoable(new CheckBoxStyleUndoable(style));
    }
    
    public void checkBoxText(String text) {
        processUndoable(new CheckBoxTextUndoable(text));
    }
    
    public void containerAlignment(int alignment) {
        processUndoable(new ContainerAlignmentUndoable(alignment));
    }
    
    public void containerBackground(DrawableData background) {
        processUndoable(new ContainerBackgroundUndoable(background));
    }
    
    public void containerDelete() {
        processUndoable(new ContainerDeleteUndoable());
    }
    
    public void containerFill(boolean fillX, boolean fillY) {
        processUndoable(new ContainerFillUndoable(fillX, fillY));
    }
    
    public void containerName(String name) {
        processUndoable(new ContainerNameUndoable(name));
    }
    
    public void containerPadding(float padLeft, float padRight, float padTop, float padBottom) {
        processUndoable(new ContainerPaddingUndoable(padLeft,  padRight, padTop, padBottom));
    }
    
    public void containerReset() {
        processUndoable(new ContainerResetUndoable());
    }
    
    public void containerSetWidget(WidgetType widgetType) {
        processUndoable(new ContainerSetWidgetUndoable(widgetType));
    }
    
    public void containerSize(float minWidth, float minHeight, float maxWidth, float maxHeight, float preferredWidth, float preferredHeight) {
        processUndoable(new ContainerSizeUndoable(minWidth, minHeight, maxWidth, maxHeight, preferredWidth, preferredHeight));
    }
    
    public void horizontalGroupAlignment(int alignment) {
        processUndoable(new HorizontalGroupAlignmentUndoable(alignment));
    }
    
    public void horizontalGroupChildren() {
    
    }
    
    public void horizontalGroupDelete() {
        processUndoable(new HorizontalGroupDeleteUndoable());
    }
    
    public void horizontalGroupExpand(boolean expand) {
        processUndoable(new HorizontalGroupExpandUndoable(expand));
    }
    
    public void horizontalGroupFill(float fill) {
        processUndoable(new HorizontalGroupFillUndoable(fill));
    }
    
    public void horizontalGroupName(String name) {
        processUndoable(new HorizontalGroupNameUndoable(name));
    }
    
    public void horizontalGroupPadBottom(float padBottom) {
        processUndoable(new HorizontalGroupPadBottomUndoable(padBottom));
    }
    
    public void horizontalGroupPadLeft(float padLeft) {
        processUndoable(new HorizontalGroupPadLeftUndoable(padLeft));
    }
    
    public void horizontalGroupPadRight(float padRight) {
        processUndoable(new HorizontalGroupPadRightUndoable(padRight));
    }
    
    public void horizontalGroupPadTop(float padTop) {
        processUndoable(new HorizontalGroupPadTopUndoable(padTop));
    }
    
   public void horizontalGroupReset() {
        processUndoable(new HorizontalGroupResetUndoable());
   }
   
   public void horizontalGroupReverse(boolean reverse) {
        processUndoable(new HorizontalGroupReverseUndoable(reverse));
   }
   
   public void horizontalGroupRowAlignment(int rowAlignment) {
        processUndoable(new HorizontalGroupRowAlignmentUndoable(rowAlignment));
   }
   
   public void horizontalGroupSpace(float space) {
        processUndoable(new HorizontalGroupSpaceUndoable(space));
   }
   
   public void horizontalGroupWrapSpace(float wrapSpace) {
        processUndoable(new HorizontalGroupWrapSpaceUndoable(wrapSpace));
   }
   
   public void horizontalGroupWrap(boolean wrap) {
        processUndoable(new HorizontalGroupWrapUndoable(wrap));
   }
   
   public void imageDrawable(DrawableData drawable) {
        processUndoable(new ImageDrawableUndoable(drawable));
   }
   
   public void imageDelete() {
        processUndoable(new ImageDeleteUndoable());
   }
   
   public void imageName(String name) {
        processUndoable(new ImageNameUndoable(name));
   }
   
   public void imageReset() {
        processUndoable(new ImageResetUndoable());
   }
   
   public void imageScaling(Scaling scaling) {
        processUndoable(new ImageScalingUndoable(scaling));
   }
   
   public void labelAlignment(int alignment) {
        processUndoable(new LabelAlignmentUndoable(alignment));
   }
   
   public void labelColor(ColorData color) {
        processUndoable(new LabelColorUndoable(color));
   }
   
   public void labelDelete() {
        processUndoable(new LabelDeleteUndoable());
   }
   
   public void labelEllipsis(boolean ellipsis, String ellipsisString) {
        processUndoable(new LabelEllipsisUndoable(ellipsis, ellipsisString));
   }
   
   public void labelName(String name) {
        processUndoable(new LabelNameUndoable(name));
   }
   
   public void labelReset() {
        processUndoable(new LabelResetUndoable());
   }
   
   public void labelStyle(StyleData style) {
        processUndoable(new LabelStyleUndoable(style));
   }
   
   public void labelText(String text) {
        processUndoable(new LabelTextUndoable(text));
   }
   
   public void labelWrap(boolean wrap) {
        processUndoable(new LabelWrapUndoable(wrap));
   }
   
   public void listDelete() {
        processUndoable(new ListDeleteUndoable());
   }
   
   public void listList(Array<String> textList) {
        processUndoable(new ListListUndoable(textList));
   }
   
   public void listName(String name) {
        processUndoable(new ListNameUndoable(name));
   }
   
   public void listReset() {
        processUndoable(new ListResetUndoable());
   }
   
   public void listStyle(StyleData style) {
        processUndoable(new ListStyleUndoable(style));
   }
   
   public void nodeDelete() {
        processUndoable(new NodeDeleteUndoable());
   }
   
   public void nodeExpanded(boolean expanded) {
        processUndoable(new NodeExpandedUndoable(expanded));
   }
   
   public void nodeIcon(DrawableData icon) {
        processUndoable(new NodeIconUndoable(icon));
   }
   
   public void nodeReset() {
        processUndoable(new NodeResetUndoable());
   }
   
   public void nodeSelectable(boolean selectable) {
        processUndoable(new NodeSelectableUndoable(selectable));
   }
   
   public void nodeSetNodes() {
   
   }
   
   public void nodeSetWidget(WidgetType widgetType) {
        processUndoable(new NodeSetWidgetUndoable(widgetType));
   }
   
   public void progressBarAnimateInterpolation(DialogSceneComposerModel.Interpol interpol) {
        processUndoable(new ProgressBarAnimateInterpolationUndoable(interpol));
   }
   
   public void progressBarAnimationDuration(float animationDuration) {
        processUndoable(new ProgressBarAnimationDurationUndoable(animationDuration));
   }
   
   public void progressBarDelete() {
        processUndoable(new ProgressBarDeleteUndoable());
   }
   
   public void progressBarDisabled(boolean disabled) {
        processUndoable(new ProgressBarDisabledUndoable(disabled));
   }
   
   public void progressBarIncrement(float increment) {
        processUndoable(new ProgressBarIncrementUndoable(increment));
   }
   
   public void progressBarMaximum(float maximum) {
        processUndoable(new ProgressBarMaximumUndoable(maximum));
   }
   
   public void progressBarMinimum(float minimum) {
        processUndoable(new ProgressBarMinimumUndoable(minimum));
   }
   
   public void progressBarName(String name) {
        processUndoable(new ProgressBarNameUndoable(name));
   }
   
   public void progressBarReset() {
        processUndoable(new ProgressBarResetUndoable());
   }
   
   public void progressBarRound(boolean round) {
        processUndoable(new ProgressBarRoundUndoable(round));
   }
   
   public void progressBarStyle(StyleData style) {
        processUndoable(new ProgressBarStyleUndoable(style));
   }
   
   public void progressBarValue(float value) {
        processUndoable(new ProgressBarValueUndoable(value));
   }
   
   public void progressBarVertical(boolean vertical) {
        processUndoable(new ProgressBarVerticalUndoable(vertical));
   }
   
   public void progressBarVisualInterpolation(DialogSceneComposerModel.Interpol visualInterpolation) {
        processUndoable(new ProgressBarVisualInterpolationUndoable(visualInterpolation));
   }
   
   public void scrollPaneClamp(boolean clamp) {
        processUndoable(new ScrollPaneClampUndoable(clamp));
   }
   
   public void scrollPaneDelete() {
        processUndoable(new ScrollPaneDeleteUndoable());
   }
   
   public void scrollPaneFadeScrollBars(boolean fadeScrollBars) {
        processUndoable(new ScrollPaneFadeScrollBarsUndoable(fadeScrollBars));
   }
   
   public void scrollPaneFlickScroll(boolean flickScroll) {
        processUndoable(new ScrollPaneFlickScrollUndoable(flickScroll));
   }
   
   public void scrollPaneFlingTime(float flingTime) {
        processUndoable(new ScrollPaneFlingTimeUndoable(flingTime));
   }
   
   public void scrollPaneForceOverScrollX(boolean forceOverScrollX) {
        processUndoable(new ScrollPaneForceOverScrollXUndoable(forceOverScrollX));
   }
   
   public void scrollPaneForceOverScrollY(boolean forceOverScrollY) {
        processUndoable(new ScrollPaneForceOverScrollYUndoable(forceOverScrollY));
   }
   
   public void scrollPaneForceScrollX(boolean forceScrollX) {
        processUndoable(new ScrollPaneForceScrollXUndoable(forceScrollX));
   }
   
   public void scrollPaneForceScrollY(boolean forceScrollY) {
        processUndoable(new ScrollPaneForceScrollYUndoable(forceScrollY));
   }
   
   public void scrollPaneName(String name) {
        processUndoable(new ScrollPaneNameUndoable(name));
   }
   
   public void scrollPaneOverScrollDistance(float overScrollDistance) {
        processUndoable(new ScrollPaneOverScrollDistanceUndoable(overScrollDistance));
   }
   
   public void scrollPaneOverScrollSpeedMax(float overScrollSpeedMax) {
        processUndoable(new ScrollPaneOverScrollSpeedMaxUndoable(overScrollSpeedMax));
   }
   
   public void scrollPaneOverScrollSpeedMin(float overScrollSpeedMin) {
        processUndoable(new ScrollPaneOverScrollSpeedMinUndoable(overScrollSpeedMin));
   }
   
   public void scrollPaneReset() {
        processUndoable(new ScrollPaneResetUndoable());
   }
   
   public void scrollPaneScrollBarBottom(boolean scrollBarBottom) {
        processUndoable(new ScrollPaneScrollBarBottomUndoable(scrollBarBottom));
   }
   
   public void scrollPaneScrollBarRight(boolean scrollBarRight) {
        processUndoable(new ScrollPaneScrollBarRightUndoable(scrollBarRight));
   }
   
   public void scrollPaneScrollBarsOnTop(boolean scrollBarsOnTop) {
        processUndoable(new ScrollPaneScrollBarsOnTopUndoable(scrollBarsOnTop));
   }
   
   public void scrollPaneScrollBarsVisible(boolean scrollBarsVisible) {
        processUndoable(new ScrollPaneScrollBarsVisibleUndoable(scrollBarsVisible));
   }
   
   public void scrollPaneScrollBarTouch(boolean scrollBarTouch) {
        processUndoable(new ScrollPaneScrollBarTouchUndoable(scrollBarTouch));
   }
   
   public void scrollPaneScrollingDisabledX(boolean scrollingDisabledX) {
        processUndoable(new ScrollPaneScrollingDisabledXUndoable(scrollingDisabledX));
   }
   
   public void scrollPaneScrollingDisabledY(boolean scrollingDisabledY) {
        processUndoable(new ScrollPaneScrollingDisabledYUndoable(scrollingDisabledY));
   }
   
   public void scrollPaneSetWidget(WidgetType widgetType) {
        processUndoable(new ScrollPaneSetWidgetUndoable(widgetType));
   }
   
   public void scrollPaneSmoothScrolling(boolean smoothScrolling) {
        processUndoable(new ScrollPaneSmoothScrollingUndoable(smoothScrolling));
   }
   
   public void scrollPaneStyle(StyleData style) {
        processUndoable(new ScrollPaneStyleUndoable(style));
   }
   
   public void scrollPaneVariableSizeKnobs(boolean variableSizeKnobs) {
        processUndoable(new ScrollPaneVariableSizeKnobsUndoable(variableSizeKnobs));
   }
   
   public void selectBoxAlignment(int alignment) {
        processUndoable(new SelectBoxAlignmentUndoable(alignment));
   }
   
   public void selectBoxDelete() {
        processUndoable(new SelectBoxDeleteUndoable());
   }
   
   public void selectBoxDisabled(boolean disabled) {
        processUndoable(new SelectBoxDisabledUndoable(disabled));
   }
   
   public void selectBoxList(Array<String> textList) {
        processUndoable(new SelectBoxListUndoable(textList));
   }
   
   public void selectBoxMaxListCount(int maxListCount) {
        processUndoable(new SelectBoxMaxListCountUndoable(maxListCount));
   }
   
   public void selectBoxName(String name) {
        processUndoable(new SelectBoxNameUndoable(name));
   }
   
   public void selectBoxReset() {
        processUndoable(new SelectBoxResetUndoable());
   }
   
   public void selectBoxScrollingDisabled(boolean scrollingDisabled) {
        processUndoable(new SelectBoxScrollingDisabledUndoable(scrollingDisabled));
   }
   
   public void selectBoxSelected(int selected) {
        processUndoable(new SelectBoxSelectedUndoable(selected));
   }
   
   public void selectBoxStyle(StyleData style) {
        processUndoable(new SelectBoxStyleUndoable(style));
   }
    
    public void sliderAnimateInterpolation(DialogSceneComposerModel.Interpol interpol) {
        processUndoable(new SliderAnimateInterpolationUndoable(interpol));
    }
    
    public void sliderAnimationDuration(float animationDuration) {
        processUndoable(new SliderAnimationDurationUndoable(animationDuration));
    }
    
    public void sliderDelete() {
        processUndoable(new SliderDeleteUndoable());
    }
    
    public void sliderDisabled(boolean disabled) {
        processUndoable(new SliderDisabledUndoable(disabled));
    }
    
    public void sliderIncrement(float increment) {
        processUndoable(new SliderIncrementUndoable(increment));
    }
    
    public void sliderMaximum(float maximum) {
        processUndoable(new SliderMaximumUndoable(maximum));
    }
    
    public void sliderMinimum(float minimum) {
        processUndoable(new SliderMinimumUndoable(minimum));
    }
    
    public void sliderName(String name) {
        processUndoable(new SliderNameUndoable(name));
    }
    
    public void sliderReset() {
        processUndoable(new SliderResetUndoable());
    }
    
    public void sliderRound(boolean round) {
        processUndoable(new SliderRoundUndoable(round));
    }
    
    public void sliderStyle(StyleData style) {
        processUndoable(new SliderStyleUndoable(style));
    }
    
    public void sliderValue(float value) {
        processUndoable(new SliderValueUndoable(value));
    }
    
    public void sliderVertical(boolean vertical) {
        processUndoable(new SliderVerticalUndoable(vertical));
    }
    
    public void sliderVisualInterpolation(DialogSceneComposerModel.Interpol visualInterpolation) {
        processUndoable(new SliderVisualInterpolationUndoable(visualInterpolation));
    }
    
    public void splitPaneChildFirst(WidgetType widgetType) {
        processUndoable(new SplitPaneChildFirstUndoable(widgetType));
    }
    
    public void splitPaneChildSecond(WidgetType widgetType) {
        processUndoable(new SplitPaneChildSecondUndoable(widgetType));
    }
    
    public void splitPaneDelete() {
        processUndoable(new SplitPaneDeleteUndoable());
    }
    
    public void splitPaneName(String name) {
        processUndoable(new SplitPaneNameUndoable(name));
    }
    
    public void splitPaneReset() {
        processUndoable(new SplitPaneResetUndoable());
    }
    
    public void splitPaneSplitMax(float splitMax) {
        processUndoable(new SplitPaneSplitMaxUndoable(splitMax));
    }
    
    public void splitPaneSplitMin(float splitMin) {
        processUndoable(new SplitPaneSplitMinUndoable(splitMin));
    }
    
    public void splitPaneSplit(float split) {
        processUndoable(new SplitPaneSplitUndoable(split));
    }
    
    public void splitPaneStyle(StyleData style) {
        processUndoable(new SplitPaneStyleUndoable(style));
    }
    
    public void splitPaneVertical(boolean vertical) {
        processUndoable(new SplitPaneVerticalUndoable(vertical));
    }
    
    public void stackChildren() {
    
    }
    
    public void stackDelete() {
        processUndoable(new StackDeleteUndoable());
    }
    
    public void stackName(String name) {
        processUndoable(new StackNameUndoable(name));
    }
    
    public void stackReset() {
        processUndoable(new StackResetUndoable());
    }
    
    public void textAreaAlignment(int alignment) {
        processUndoable(new TextAreaAlignmentUndoable(alignment));
    }
    
    public void textAreaCursorPosition(int cursorPosition) {
        processUndoable(new TextAreaCursorPositionUndoable(cursorPosition));
    }
    
    public void textAreaDelete() {
        processUndoable(new TextAreaDeleteUndoable());
    }
    
    public void textAreaDisabled(boolean disabled) {
        processUndoable(new TextAreaDisabledUndoable(disabled));
    }
    
    public void textAreaFocusTraversal(boolean focusTraversal) {
        processUndoable(new TextAreaFocusTraversalUndoable(focusTraversal));
    }
    
    public void textAreaMaxLength(int maxLength) {
        processUndoable(new TextAreaMaxLengthUndoable(maxLength));
    }
    
    public void textAreaMessageText(String messageText) {
        processUndoable(new TextAreaMessageTextUndoable(messageText));
    }
    
    public void textAreaName(String name) {
        processUndoable(new TextAreaNameUndoable(name));
    }
    
    public void textAreaPasswordCharacter(char character) {
        processUndoable(new TextAreaPasswordCharacterUndoable(character));
    }
    
    public void textAreaPasswordMode(boolean passwordMode) {
        processUndoable(new TextAreaPasswordModeUndoable(passwordMode));
    }
    
    public void textAreaPreferredRows(int preferredRow) {
        processUndoable(new TextAreaPreferredRowUndoable(preferredRow));
    }
    
    public void textAreaReset() {
        processUndoable(new TextAreaResetUndoable());
    }
    
    public void textAreaSelectAll(boolean selectAll) {
        processUndoable(new TextAreaSelectAllUndoable(selectAll));
    }
    
    public void textAreaSelectionEnd(int selectionEnd) {
        processUndoable(new TextAreaSelectionEndUndoable(selectionEnd));
    }
    
    public void textAreaSelectionStart(int selectionStart) {
        processUndoable(new TextAreaSelectionStartUndoable(selectionStart));
    }
    
    public void textAreaStyle(StyleData style) {
        processUndoable(new TextAreaStyleUndoable(style));
    }
    
    public void textAreaText(String text) {
        processUndoable(new TextAreaTextUndoable(text));
    }
    
    public void textFieldAlignment(int alignment) {
        processUndoable(new TextFieldAlignmentUndoable(alignment));
    }
    
    public void textFieldCursorPosition(int cursorPosition) {
        processUndoable(new TextFieldCursorPositionUndoable(cursorPosition));
    }
    
    public void textFieldDelete() {
        processUndoable(new TextFieldDeleteUndoable());
    }
    
    public void textFieldDisabled(boolean disabled) {
        processUndoable(new TextFieldDisabledUndoable(disabled));
    }
    
    public void textFieldFocusTraversal(boolean focusTraversal) {
        processUndoable(new TextFieldFocusTraversalUndoable(focusTraversal));
    }
    
    public void textFieldMaxLength(int maxLength) {
        processUndoable(new TextFieldMaxLengthUndoable(maxLength));
    }
    
    public void textFieldMessageText(String messageText) {
        processUndoable(new TextFieldMessageTextUndoable(messageText));
    }
    
    public void textFieldName(String name) {
        processUndoable(new TextFieldNameUndoable(name));
    }
    
    public void textFieldPasswordCharacter(char character) {
        processUndoable(new TextFieldPasswordCharacterUndoable(character));
    }
    
    public void textFieldPasswordMode(boolean passwordMode) {
        processUndoable(new TextFieldPasswordModeUndoable(passwordMode));
    }
    
    public void textFieldReset() {
        processUndoable(new TextFieldResetUndoable());
    }
    
    public void textFieldSelectAll(boolean selectAll) {
        processUndoable(new TextFieldSelectAllUndoable(selectAll));
    }
    
    public void textFieldSelectionEnd(int selectionEnd) {
        processUndoable(new TextFieldSelectionEndUndoable(selectionEnd));
    }
    
    public void textFieldSelectionStart(int selectionStart) {
        processUndoable(new TextFieldSelectionStartUndoable(selectionStart));
    }
    
    public void textFieldStyle(StyleData style) {
        processUndoable(new TextFieldStyleUndoable(style));
    }
    
    public void textFieldText(String text) {
        processUndoable(new TextFieldTextUndoable(text));
    }
    
    public void touchPadDelete() {
        processUndoable(new TouchPadDeleteUndoable());
    }
    
    public void touchPadName(String name) {
        processUndoable(new TouchPadNameUndoable(name));
    }
    
    public void touchPadPadding(float deadZone) {
        processUndoable(new TouchPadDeadZoneUndoable(deadZone));
    }
    
    public void touchPadResetOnTouchUp(boolean resetOnTouchUp) {
        processUndoable(new TouchPadResetOnTouchUpUndoable(resetOnTouchUp));
    }
    
    public void touchPadReset() {
        processUndoable(new TouchPadResetUndoable());
    }
    
    public void touchPadStyle(StyleData style) {
        processUndoable(new TouchPadStyleUndoable(style));
    }
    
    public void treeChildren() {
    
    }
    
    public void treeDelete() {
        processUndoable(new TreeDeleteUndoable());
    }
    
    public void treeIconSpaceLeft(float iconSpaceLeft) {
        processUndoable(new TreeIconSpaceLeftUndoable(iconSpaceLeft));
    }
    
    public void treeIconSpaceRight(float iconSpaceRight) {
        processUndoable(new TreeIconSpaceRightUndoable(iconSpaceRight));
    }
    
    public void treeIndentSpacing(float indentSpacing) {
        processUndoable(new TreeIndentSpacingUndoable(indentSpacing));
    }
    
    public void treeName(String name) {
        processUndoable(new TreeNameUndoable(name));
    }
    
    public void treePadLeft(float padLeft) {
        processUndoable(new TreePadLeftUndoable(padLeft));
    }
    
    public void treePadRight(float padRight) {
        processUndoable(new TreePadRightUndoable(padRight));
    }
    
    public void treeReset() {
        processUndoable(new TreeResetUndoable());
    }
    
    public void treeStyle(StyleData style) {
        processUndoable(new TreeStyleUndoable(style));
    }
    
    public void treeYSpacing(float ySpacing) {
        processUndoable(new TreeYSpacingUndoable(ySpacing));
    }
    
    public void verticalGroupAlignment(int alignment) {
        processUndoable(new VerticalGroupAlignmentUndoable(alignment));
    }
    
    public void verticalGroupChildren() {
    
    }
    
    public void verticalGroupDelete() {
        processUndoable(new VerticalGroupDeleteUndoable());
    }
    
    public void verticalGroupExpand(boolean expand) {
        processUndoable(new VerticalGroupExpandUndoable(expand));
    }
    
    public void verticalGroupFill(float fill) {
        processUndoable(new VerticalGroupFillUndoable(fill));
    }
    
    public void verticalGroupName(String name) {
        processUndoable(new VerticalGroupNameUndoable(name));
    }
    
    public void verticalGroupPadBottom(float padBottom) {
        processUndoable(new VerticalGroupPadBottomUndoable(padBottom));
    }
    
    public void verticalGroupPadLeft(float padLeft) {
        processUndoable(new VerticalGroupPadLeftUndoable(padLeft));
    }
    
    public void verticalGroupPadRight(float padRight) {
        processUndoable(new VerticalGroupPadRightUndoable(padRight));
    }
    
    public void verticalGroupPadTop(float padTop) {
        processUndoable(new VerticalGroupPadTopUndoable(padTop));
    }
    
    public void verticalGroupReset() {
        processUndoable(new VerticalGroupResetUndoable());
    }
    
    public void verticalGroupReverse(boolean reverse) {
        processUndoable(new VerticalGroupReverseUndoable(reverse));
    }
    
    public void verticalGroupRowAlignment(int columnAlignment) {
        processUndoable(new VerticalGroupColumnAlignmentUndoable(columnAlignment));
    }
    
    public void verticalGroupSpace(float space) {
        processUndoable(new VerticalGroupSpaceUndoable(space));
    }
    
    public void verticalGroupWrapSpace(float wrapSpace) {
        processUndoable(new VerticalGroupWrapSpaceUndoable(wrapSpace));
    }
    
    public void verticalGroupWrap(boolean wrap) {
        processUndoable(new VerticalGroupWrapUndoable(wrap));
    }
}