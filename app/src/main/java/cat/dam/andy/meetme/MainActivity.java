package cat.dam.andy.meetme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
//Members
private ImageView menu_map, menu_profile, menu_matches;
    private ArrayList<PermissionData> permissionsRequired=new ArrayList<>();
    private PermissionManager permissionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initPermissions();
        initListeners();
        initMap();

    }

    private void initViews() {
        menu_map = findViewById(R.id.menu_map);
        menu_profile = findViewById(R.id.menu_profile);
        menu_matches = findViewById(R.id.menu_matches);
    }

    private void initPermissions() {
        //TO DO: CONFIGURE ALL NECESSARY PERMISSIONS

        //BEGIN
        permissionsRequired.add(new PermissionData(Manifest.permission.ACCESS_FINE_LOCATION,
                getString(R.string.locationPermissionNeeded),
                "",
                getString(R.string.locationPermissionThanks),
                getString(R.string.locationPermissionSettings)));

        //END
        //DON'T DELETE == call permission manager ==
        permissionManager= new PermissionManager(this, permissionsRequired);
    }

    public PermissionManager getPermissionManager() {
        return permissionManager;
    }

    public ArrayList<PermissionData> getPermissionsRequired() {
        return permissionsRequired;
    }

    private void initListeners() {
        menu_map.setOnClickListener(v -> {
            showMapMenu();
        });
        menu_profile.setOnClickListener(v -> {
            showProfileMenu();
        });
        menu_matches.setOnClickListener(v -> {
            showMatchesMenu();
        });
    }

    private void initMap() {
        //Inicialitza fragment
        Fragment mapFragment = new MapFragment();
        //Obre fragment al frame del layout
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_frame, mapFragment)
                .commit();
    }

    void showMapMenu(){
        Fragment mapFragment = new MapFragment();
        //Obre fragment al frame del layout
        this.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_frame, mapFragment)
                .commit();
    }

    void showProfileMenu(){
        Fragment profileFragment = new ProfileFragment();
        //Obre fragment al frame del layout
        this.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_frame, profileFragment)
                .commit();
    }

    void showMatchesMenu(){
        Fragment matchesFragment = new MatchesFragment();
        //Obre fragment al frame del layout
        this.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_frame, matchesFragment)
                .commit();
    }
}