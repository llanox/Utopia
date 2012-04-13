package co.edu.udea.ludens.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.component.UIData;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import co.edu.udea.ludens.domain.Incrementable;
import co.edu.udea.ludens.domain.IncrementableConstraint;
import co.edu.udea.ludens.enums.EnumElementType;
import co.edu.udea.ludens.exceptions.LudensException;
import co.edu.udea.ludens.services.ElementService;
import co.edu.udea.ludens.services.IncrementableService;
import co.edu.udea.ludens.util.UpdateableView;

import com.icesoft.faces.component.inputfile.InputFile;
import common.Logger;

public class IncrementableController implements UpdateableView{

	private Logger logger = Logger.getLogger(getClass());
	private Incrementable actualIncrementable = new Incrementable();
	private IncrementableConstraint actualConstraint;

   
	private boolean listingElements = true;
	private boolean editingElement=false;
	private boolean addingIncrementableImage=false;


	private UIData incrementablesTable;
	private UIData imagesIncrementableTable;
	
	
	private static final String FACTORES_LABEL = "Factores";
	private static final String RECURSOS_LABEL = "Materiales";
	private String[] typesNames = { FACTORES_LABEL, RECURSOS_LABEL };
	private List<SelectItem> incrementableTypes = new ArrayList<SelectItem>();
	private List<SelectItem> materialItems = new ArrayList<SelectItem>();
	private List<Incrementable> incrementables = new ArrayList<Incrementable>();


	private GameController gameController;
	

	private String constraintMaterial;

	private IncrementableService incrementableService;
	private ElementService elementService;

	@PostConstruct
	public void loadIncrementables() {

		if (incrementableTypes == null || incrementableTypes.isEmpty()) {

			EnumElementType[] types = EnumElementType.values();

			for (EnumElementType type : types) {
				incrementableTypes.add(new SelectItem(type.getType(), type.getType()));
			}
		}

		incrementables = incrementableService.getAllIncrementablesGame(gameController.getActualGame().getName());
		
		for(Incrementable incr :incrementables){
			
			logger.info("Incrementable image: "+incr.getImageUrl());
			
		}


	}
	

	







	public void addImageIncrementable(javax.faces.event.ActionEvent event) {
		
		incrementableService.save(actualIncrementable);
        this.addingIncrementableImage = false;
	}
	


	public void cancelAddImage(javax.faces.event.ActionEvent event) {

		this.addingIncrementableImage=false;
	
	}
	
	
	public void settingUpGame(javax.faces.event.ActionEvent event) {
    this.gameController.settingUpGame(event);
	listingElements = true;
	editingElement=false;
	addingIncrementableImage=false;
    
    
    
    loadIncrementables();

	}
	
	public void addConstraint(javax.faces.event.ActionEvent event) throws LudensException{
		
		logger.info("adding constraint....");
		
		IncrementableConstraint constraint = new IncrementableConstraint();
		constraint.setRestrictedIncrementable(actualIncrementable);		
		actualIncrementable.getConstraints().add(constraint);
		incrementableService.save(actualIncrementable);
		
		incrementableService.createResourceConstraints(actualIncrementable);
	
	}


	public void saveIncrementable(javax.faces.event.ActionEvent event) {
		editingElement = false;
		
		
		logger.info("Incrementable type to save: "+ actualIncrementable.getType());
		actualIncrementable.setGame(gameController.getActualGame());
		incrementableService.save(actualIncrementable);
		
		actualIncrementable = new Incrementable();
		loadIncrementables();
		
	}

	public void newIncrementable(javax.faces.event.ActionEvent event) throws LudensException {
		editingElement = true;
		actualIncrementable = new Incrementable();
		actualIncrementable.setType(EnumElementType.MATERIAL);
		actualIncrementable.setGame(gameController.getActualGame());
		
		logger.info("new incrementable");
	
		incrementableService.createResourceConstraints(actualIncrementable);
	}

	public void editingIncrementable(javax.faces.event.ActionEvent event) throws LudensException {
		editingElement = true;
		int selectedRowIndex = incrementablesTable.getRowIndex();
		logger.info("Selected row" + selectedRowIndex);
		actualIncrementable = incrementables.get(selectedRowIndex);
		logger.info("Selected " + actualIncrementable.getName());


		incrementableService.createResourceConstraints(actualIncrementable);
	}
	
	


	public void listElements(javax.faces.event.ActionEvent event) {
		this.listingElements = true;
	
	}
	
	
	public void addNewImage(javax.faces.event.ActionEvent event) {
		this.addingIncrementableImage=true;
		
	}
	

	public void deleteIncrementable(javax.faces.event.ActionEvent event) {

		int selectedRowIndex = incrementablesTable.getRowIndex();
		logger.info("Selected row" + selectedRowIndex);
		
		if(selectedRowIndex<0)
			return;
		
		Incrementable incr = incrementables.get(selectedRowIndex);
		logger.info("Incrementable to delete " + incr.getName());
		incrementableService.delete(incr);

		loadIncrementables();


	}
	

	

	public void cancelEditing(javax.faces.event.ActionEvent event) {
		editingElement = false;
		actualIncrementable = new Incrementable();

		loadIncrementables();
		

	}
	
	
	public void uploadActionListener(ActionEvent actionEvent) throws LudensException {
        InputFile inputFile = (InputFile) actionEvent.getSource();          
        
                       
        if (!inputFile.getFileInfo().isSaved()) {
            throw new LudensException("No fue posible guardar la imágen");
        }
        
        
        actualIncrementable.setImageUrl(inputFile.getFileInfo().getFile().getName());
	}
	




	public void changeType(ValueChangeEvent vce) {
		logger.info("changeType ");
		logger.info("Old value " + vce.getOldValue());
		logger.info("New value " + vce.getNewValue());	

			if (vce.getNewValue()!=null) {
				EnumElementType type = EnumElementType.getElementType(vce.getNewValue().toString());
				logger.info("Setting type " + type);
				actualIncrementable.setType(type);
				
			
			}

		
	}

	/**
	 * @param typesNames
	 *            the typesNames to set
	 */
	public void setTypesNames(String[] typesNames) {
		this.typesNames = typesNames;
	}

	/**
	 * @return the typesNames
	 */
	public String[] getTypesNames() {
		return typesNames;
	}

	public Incrementable getActualIncrementable() {
		return actualIncrementable;
	}

	

	public List<SelectItem> getIncrementableTypes() {
		return incrementableTypes;
	}

	public List<Incrementable> getIncrementables() {
		return incrementables;
	}

	public void setActualIncrementable(Incrementable actualIncrementable) {
		this.actualIncrementable = actualIncrementable;
	}



	public void setIncrementableTypes(List<SelectItem> incrementableTypes) {
		this.incrementableTypes = incrementableTypes;
	}

	public void setIncrementables(List<Incrementable> incrementables) {
		this.incrementables = incrementables;
	}

	/**
	 * @param incrementablesTable
	 *            the incrementablesTable to set
	 */
	public void setIncrementablesTable(UIData incrementablesTable) {
		this.incrementablesTable = incrementablesTable;
	}

	/**
	 * @return the incrementablesTable
	 */
	public UIData getIncrementablesTable() {
		return incrementablesTable;
	}

	/**
	 * @param incrementableService
	 *            the incrementableService to set
	 */
	public void setIncrementableService(
			IncrementableService incrementableService) {
		this.incrementableService = incrementableService;
	}

	/**
	 * @return the incrementableService
	 */
	public IncrementableService getIncrementableService() {
		return incrementableService;
	}



	

	/**
	 * @param imagesIncrementableTable
	 *            the imagesIncrementableTable to set
	 */
	public void setImagesIncrementableTable(UIData imagesIncrementableTable) {
		this.imagesIncrementableTable = imagesIncrementableTable;
	}

	/**
	 * @return the imagesIncrementableTable
	 */
	public UIData getImagesIncrementableTable() {
		return imagesIncrementableTable;
	}




	/**
	 * @param gameController the gameController to set
	 */
	public void setGameController(GameController gameController) {
		this.gameController = gameController;
	}


	/**
	 * @return the gameController
	 */
	public GameController getGameController() {
		return gameController;
	}






	public boolean isListingElements() {
		return listingElements;
	}


	public boolean isEditingElement() {
		return editingElement;
	}


	public boolean isAddingIncrementableImage() {
		return addingIncrementableImage;
	}


	public void setListingElements(boolean listingElements) {
		this.listingElements = listingElements;
	}


	public void setEditingElement(boolean editingElement) {
		this.editingElement = editingElement;
	}


	public void setAddingIncrementableImage(boolean addingIncrementableImage) {
		this.addingIncrementableImage = addingIncrementableImage;
	}


	/**
	 * @param elementService the elementService to set
	 */
	public void setElementService(ElementService elementService) {
		this.elementService = elementService;
	}


	/**
	 * @return the elementService
	 */
	public ElementService getElementService() {
		return elementService;
	}


	@Override
	public void update() {
		logger.info("Updating IncrementableController");
		loadIncrementables();
		
	}


	/**
	 * @param materials the materials to set
	 */
	public void setMaterials(List<SelectItem> materials) {
		this.materialItems = materials;
	}


	public List<SelectItem> getMaterialItems() {
		return materialItems;
	}


	public void setMaterialItems(List<SelectItem> materialItems) {
		this.materialItems = materialItems;
	}


	/**
	 * @return the materials
	 */
	public List<SelectItem> getMaterials() {
		return materialItems;
	}


	/**
	 * @param actualConstraint the actualConstraint to set
	 */
	public void setActualConstraint(IncrementableConstraint actualConstraint) {
		this.actualConstraint = actualConstraint;
	}


	/**
	 * @return the actualConstraint
	 */
	public IncrementableConstraint getActualConstraint() {
		return actualConstraint;
	}


	/**
	 * @param constraintMaterial the constraintMaterial to set
	 */
	public void setConstraintMaterial(String constraintMaterial) {
		this.constraintMaterial = constraintMaterial;
	}


	/**
	 * @return the constraintMaterial
	 */
	public String getConstraintMaterial() {
		return constraintMaterial;
	}




	




}
