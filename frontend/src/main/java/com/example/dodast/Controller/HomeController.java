package com.example.dodast.Controller;

import com.example.dodast.DTO.Advertisement.AdSearchRequest;
import com.example.dodast.DTO.Advertisement.AdvertisementResponse;
import com.example.dodast.DTO.Advertisement.OptionResponse;
import com.example.dodast.Exception.ShowAlert;
import com.example.dodast.Model.SearchSortBy;
import com.example.dodast.Service.AdvertisementService;
import com.example.dodast.Service.SearchService;
import com.example.dodast.Util.AdvertisementCard;
import com.example.dodast.Util.AdvertisementFormSession;
import com.example.dodast.Util.AdvertisementSession;
import com.example.dodast.Util.NavigationSession;
import com.example.dodast.Util.SceneManager;
import com.example.dodast.Util.SearchSession;
import com.example.dodast.Util.SessionManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    private Label usernameLabel;

    @FXML
    private FlowPane advertisementsPane;

    @FXML
    private Label messageLabel;

    @FXML
    private TextField keywordField;

    @FXML
    private ComboBox<OptionResponse> categoryComboBox;

    @FXML
    private ComboBox<OptionResponse> provinceComboBox;

    @FXML
    private ComboBox<OptionResponse> cityComboBox;

    @FXML
    private ComboBox<SearchSortBy> sortComboBox;

    @FXML
    private TextField minPriceField;

    @FXML
    private TextField maxPriceField;

    private final AdvertisementService advertisementService = new AdvertisementService();

    private final SearchService searchService = new SearchService();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        usernameLabel.setText(SessionManager.getUsername());
        loadSearchOptions();
        loadAdvertisements();
    }

    private void showAdvertisementDetail(Long advertisementId) {
        try {
            NavigationSession.setPreviousPage("home.fxml");
            AdvertisementSession.setSelectedAdvertisementId(advertisementId);

            Stage stage = (Stage) advertisementsPane.getScene().getWindow();

            SceneManager.switchScene(stage, "advertisement-detail.fxml");

        } catch (Exception e) {
            ShowAlert.showError("خطایی در نشان دادن آگهی پیش آمد");
            e.printStackTrace();
        }
    }

    private void loadAdvertisements() {

        advertisementsPane.getChildren().clear();

        try {
            if(SearchSession.getLastSearch() == null){
                List<AdvertisementResponse> advertisements = advertisementService.getActiveAdvertisements();
                showAdvertisements(advertisements);
            }
            else{
                List<AdvertisementResponse> advertisements = searchService.search(SearchSession.getLastSearch());
                if(SearchSession.getLastSearch().getKeyword() != null)
                    keywordField.setText(SearchSession.getLastSearch().getKeyword());
                if(SearchSession.getLastSearch().getSortBy() != null)
                    sortComboBox.setValue(SearchSession.getLastSearch().getSortBy());
                if (SearchSession.getLastSearch().getMinPrice() != null)
                    minPriceField.setText(String.valueOf(SearchSession.getLastSearch().getMinPrice()));
                if (SearchSession.getLastSearch().getMaxPrice() != null)
                    maxPriceField.setText(String.valueOf(SearchSession.getLastSearch().getMaxPrice()));
                if(SearchSession.getLastSearch().getCategoryId() != null)
                    categoryComboBox.setValue(advertisementService.getCategoryById(SearchSession.getLastSearch().getCategoryId()));
                if(SearchSession.getLastSearch().getProvinceId() != null)
                    provinceComboBox.setValue(advertisementService.getProvinceById(SearchSession.getLastSearch().getProvinceId()));
                if(SearchSession.getLastSearch().getCityId() != null)
                    cityComboBox.setValue(advertisementService.getCityById(SearchSession.getLastSearch().getCityId()));
                showAdvertisements(advertisements);
            }
        } catch (Exception e) {
            ShowAlert.showError("در بارگذاری آگهی مشکلی پیش آمد");
            e.printStackTrace();
        }
    }

    private void loadSearchOptions(){
        try {
            categoryComboBox.getItems().addAll(advertisementService.getCategories());
            provinceComboBox.getItems().addAll(advertisementService.getProvinces());
            sortComboBox.getItems().addAll(SearchSortBy.values());
            provinceComboBox.setOnAction(e -> {
                try {
                    OptionResponse province = provinceComboBox.getValue();
                    cityComboBox.getItems().clear();
                    cityComboBox.getItems().addAll(advertisementService.getCities(province.getId()));
                } catch(Exception ex){
                    ShowAlert.showError("در بارگذاری شهرها مشکلی پیش آمد");
                    ex.printStackTrace();
                }
            });
        }catch(Exception e){
            ShowAlert.showError("در بارگذاری دسته بندی ها یا شهر و استان مشکلی پیش آمد");
            e.printStackTrace();
        }
    }


    @FXML
    private void search(){
        try {
            AdSearchRequest request = new AdSearchRequest();

            if(keywordField.getText() != null && !keywordField.getText().isBlank())
                request.setKeyword(keywordField.getText());

            if(categoryComboBox.getValue() != null)
                request.setCategoryId(categoryComboBox.getValue().getId());

            if(provinceComboBox.getValue() != null)
                request.setProvinceId(provinceComboBox.getValue().getId());

            if(cityComboBox.getValue() != null)
                request.setCityId(cityComboBox.getValue().getId());

            if(sortComboBox.getValue() != null)
                request.setSortBy(sortComboBox.getValue());

            if (!minPriceField.getText().isBlank())
                request.setMinPrice(Long.parseLong(minPriceField.getText()));

            if (!maxPriceField.getText().isBlank())
                request.setMaxPrice(Long.parseLong(maxPriceField.getText()));

            SearchSession.setLastSearch(request);

            List<AdvertisementResponse> result = searchService.search(request);
            showAdvertisements(result);

        } catch(Exception e){
            ShowAlert.showError(e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void logout() {

        SessionManager.clearSession();

        try {
            Stage stage = (Stage) usernameLabel.getScene().getWindow();

            SceneManager.switchScene(stage, "login.fxml");

        } catch (Exception e) {
            ShowAlert.showError("در خروج مشکلی پیش آمد");
            e.printStackTrace();
        }
    }

    @FXML
    private void showFavorites() {
        try {
            Stage stage = (Stage) advertisementsPane.getScene().getWindow();
            SceneManager.switchScene(stage, "favorites.fxml");
        } catch (Exception e) {
            ShowAlert.showError("خطایی در نشان دادن علاقه‌مندی ها پیش آمد");
            e.printStackTrace();
        }
        
    }

    @FXML
    private void openMyAdvertisements() {
        try {
            Stage stage = (Stage) advertisementsPane.getScene().getWindow();
            SceneManager.switchScene(stage, "my-advertisements.fxml");
        } catch (Exception e) {
            ShowAlert.showError("در باز نشان دادن آگهی های شما مشکلی پیش آمد");
            e.printStackTrace();
        }
    }

    @FXML
    private void openCreateAdvertisement() {

        try {
            Stage stage = (Stage) usernameLabel.getScene().getWindow();

            AdvertisementFormSession.openCreate();
            SceneManager.switchScene(stage,"advertisement-form.fxml");

        } catch (Exception e) {
            ShowAlert.showError("خطایی در ورود به صفحه درست کردن آگهی پیش آمد");
            e.printStackTrace();
        }
    }

    @FXML
    private void goToMessages() {

        try {
            Stage stage = (Stage) usernameLabel.getScene().getWindow();

            SceneManager.switchScene(stage, "messages.fxml");

        } catch (Exception e) {
            ShowAlert.showError(e.getMessage());
        }
    }

    private void showAdvertisements(List<AdvertisementResponse> advertisements){

        advertisementsPane.getChildren().clear();

        if(advertisements == null || advertisements.isEmpty()){
            showMessage("آگهی‌ای پیدا نشد");
            return;
        }

        hideMessage();

        for(AdvertisementResponse advertisement : advertisements){
            AdvertisementCard card = new AdvertisementCard(advertisement,() -> showAdvertisementDetail(advertisement.getId()));
            advertisementsPane.getChildren().add(card.getView());
        }
    }

    @FXML
    private void clearCategory(){
        categoryComboBox.getSelectionModel().clearSelection();
    }


    @FXML
    private void clearProvince(){
        provinceComboBox.getSelectionModel().clearSelection();
        cityComboBox.getSelectionModel().clearSelection();
        cityComboBox.getItems().clear();
    }


    @FXML
    private void clearCity(){
        cityComboBox.getSelectionModel().clearSelection();
    }

    private void hideMessage() {
        messageLabel.setManaged(false);
        messageLabel.setVisible(false);
    }

    private void showMessage(String message) {
        messageLabel.setText(message);
        messageLabel.setManaged(true);
        messageLabel.setVisible(true);
    }
}
