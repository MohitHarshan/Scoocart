package com.example.mohit.scoocart;

import android.content.Intent;
import android.graphics.drawable.LayerDrawable;
import android.icu.text.UnicodeSetSpanner;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.text.SpannableString;
import android.text.style.TextAppearanceSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class navigation extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        PrivacyandPolicy.OnFragmentInteractionListener,
        Home.OnFragmentInteractionListener,
        Notificationfrag.OnFragmentInteractionListener,
        Myprofilefragment.OnFragmentInteractionListener,
        TermsandConditionsFragment.OnFragmentInteractionListener,
    CartFragment.OnFragmentInteractionListener
{

    NavigationView navigationView;
    TextView user,email;
DrawerLayout drawer;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_navigation);
        user=(TextView)findViewById(R.id.usertext);
        email=(TextView)findViewById(R.id.textView);
        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        tx.replace(R.id.containerframe, new Home());
        tx.commit();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
          onBackPressed();

      }
  });


        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer,toolbar,  R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);

        toggle.syncState();

         navigationView = (NavigationView) findViewById(R.id.nav_view);


        Menu menu = navigationView.getMenu();

        MenuItem tools= menu.findItem(R.id.shopnow);
        SpannableString s = new SpannableString(tools.getTitle());
        s.setSpan(new TextAppearanceSpan(this, R.style.TextapperanceTitle), 0, s.length(), 0);
        tools.setTitle(s);
        navigationView.setNavigationItemSelectedListener(this);


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);



        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Fragment fragment=null;
        //noinspection SimplifiableIfStatement
        if (id == R.id.search) {

        }else if(id==R.id.notify){
            fragment=Notificationfrag.newInstance("","");
            final FragmentManager manager=getSupportFragmentManager();
            FragmentTransaction transaction=manager.beginTransaction();
            transaction.replace(R.id.containerframe,fragment);
            getSupportActionBar().setTitle("Notifications");
            transaction.commit();



        }else if(id==R.id.cart){
            fragment=CartFragment.newInstance("","");
            final FragmentManager manager=getSupportFragmentManager();
            FragmentTransaction transaction=manager.beginTransaction();
            transaction.replace(R.id.containerframe,fragment);
            getSupportActionBar().setTitle("Cart");
            transaction.commit();

        }

        return super.onOptionsItemSelected(item);
    }



    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        String title="";
        title.trim();
                Fragment fragment=null;
       switch (id){
           case R.id.myprofile:  fragment= Myprofilefragment.newInstance("","");
                                 title="My Profile";
                                 break;

           case R.id.notification:    fragment= Notificationfrag.newInstance("","");
                                      title="Notifications";
                                   break;
           case R.id.terms:   fragment=TermsandConditionsFragment.newInstance("","");
                               title="Terms and Conditions";
                               break;
           case R.id.privacy : fragment=PrivacyandPolicy.newInstance("","");
                               title="Privacy and Policy";
                               break;

           case R.id.cart : fragment=CartFragment.newInstance("","");
                    title="Cart";
                    break;


               default: fragment=Home.newInstance("","");


                          title="Home";
       }

       if(id==R.id.logout){
           Toast.makeText(getApplicationContext(),"Logging out",Toast.LENGTH_SHORT).show();
           Intent intent=new Intent(getApplicationContext(),Login.class);
           startActivity(intent);

       }


            final FragmentManager manager=getSupportFragmentManager();
            FragmentTransaction transaction=manager.beginTransaction();
            transaction.replace(R.id.containerframe,fragment);
            getSupportActionBar().setTitle(title);
            transaction.commit();





        setTitle(item.getTitle());


         drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onBackPressed() {
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }

           public void shownotif(){


               android.support.v4.app.Fragment frag=Notificationfrag.newInstance("","");
               android.support.v4.app.FragmentManager man =getSupportFragmentManager();
               FragmentTransaction transaction=man.beginTransaction();
               transaction.replace(R.id.containerframe,frag).addToBackStack(null);
               transaction.commit();

           }


}



