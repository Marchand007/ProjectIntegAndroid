package com.example.projectmanager.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.example.projectmanager.R;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setSupportActionBar(findViewById(R.id.toolbar));

        NavHostFragment navHostFragment = (NavHostFragment)
                getSupportFragmentManager().findFragmentById(R.id.list_container);
        if (navHostFragment != null) {
            navController = navHostFragment.getNavController();
        }

        NavigationUI.setupActionBarWithNavController(this, navController);
    }

    @Override
    public boolean onSupportNavigateUp() {
        navController.navigateUp();
        return super.onSupportNavigateUp();
    }
}



// mettre le crochet et le rollback a place du texte
// (FAIT)

//mettre radio button priority dans le item du employeeProject pour pouvoir le
//  modifier live
//  ou on fait un layout unique avec les meme info que les details du projet mais
//  avec l'option de modifier la priority dedans (et peut etre aussi l'option
//  de marqué comme complété / réactivé dans ce layout la et la suppression)

//mettre radio button priority dans l'ajout dun projet a un employe

//mettre input pour role lorsqu on ajoute un employee a la liste
//(FAIT)

//regler le isactive qui ne change pas.
// (FAIT)

//regler le constructeur de employeeProject
// (FAIT)

// liste des projets de NOM (TROP PETIT)
// (FAIT)

// refoctoring en anglais

// faire progression bar sur le projet avec encours / complété

//renommer le employeeforprojectAdapter par employeeListInProjectAdapter
//(FAIT)

//renomme projectAdapter pour projectListforAddAdapter
//(FAIT)

// possbiliter de ajouter un employer a partir de les info du projet (reutiliser le fragment de la liste demployee
//  avec un autre boutton

// utiliser les getstring et les R.string

// ajouter le numero d'employé lors de l'ajout d'un employé