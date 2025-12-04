package modul08; 


import modul08.controller.PersegiPanjangController;
import modul08.model.PersegiPanjangModel;
import modul08.view.PersegiPanjangView;

public class Main {
    public static void main(String[] args) {
        
        PersegiPanjangModel model = new PersegiPanjangModel();

        
        PersegiPanjangView view = new PersegiPanjangView();

        
        PersegiPanjangController controller = new PersegiPanjangController(model, view);

        
        view.setVisible(true);
    }
}