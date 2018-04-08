package com.assignment.myplace.service;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.assignment.myplace.R;
import com.assignment.myplace.activities.main.MainActivity;
import com.assignment.myplace.persistence.Place;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingEvent;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GeofenceIntentService extends IntentService {

  private final String TAG = GeofenceIntentService.class.getName();

  public GeofenceIntentService() {
    super("GeofenceIntentService");
  }

  @Override
  protected void onHandleIntent(Intent intent) {

    GeofencingEvent event = GeofencingEvent.fromIntent(intent);
    if (event != null) {
      if (event.hasError()) {
        onError(event.getErrorCode());
      } else {
        int transition = event.getGeofenceTransition();
        if (transition == Geofence.GEOFENCE_TRANSITION_ENTER || transition == Geofence.GEOFENCE_TRANSITION_DWELL || transition == Geofence.GEOFENCE_TRANSITION_EXIT) {
          List<String> geofenceIds = new ArrayList<>();
          for (Geofence geofence : event.getTriggeringGeofences()) {
            geofenceIds.add(geofence.getRequestId());
          }
          if (transition == Geofence.GEOFENCE_TRANSITION_ENTER || transition == Geofence.GEOFENCE_TRANSITION_DWELL) {
            onEnteredGeofences(geofenceIds);
          }
        }
      }
    }
  }


  private void onEnteredGeofences(List<String> geofenceIds) {
    for (String geofenceId : geofenceIds) {
      String geofenceName = "";

      // Loop over all geofence keys in favorite places
      List<Place> places = GeofenceController.getInstance().getPlaceGeofences();
      for (Place p : places){
          if(p.getPlaceId().equals(geofenceId)){
            geofenceName = p.getName();
            break;
          }
      }

      // Set the notification text and send the notification
      sendNotification(geofenceName);

    }
  }

  private void onError(int i) {
    Log.e(TAG, "Geofencing Error: " + i);
  }


  private  void sendNotification(String placeName){
    // Set the notification text and send the notification
    String contextText = String.format(this.getResources().getString(R.string.Notification_Text), placeName);

    NotificationManager notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
    Intent intent = new Intent(this, MainActivity.class);
    intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
    PendingIntent pendingNotificationIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

    NotificationCompat.Builder builder = null;
    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
      int importance = NotificationManager.IMPORTANCE_DEFAULT;
      NotificationChannel notificationChannel = new NotificationChannel("com.assignment.myplace.ANDROID", "ANDROID CHANNEL", importance);
      notificationManager.createNotificationChannel(notificationChannel);
      builder = new NotificationCompat.Builder(getApplicationContext(), notificationChannel.getId());
    } else {
      builder = new NotificationCompat.Builder(getApplicationContext());
    }

    builder = builder
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(this.getResources().getString(R.string.app_name))
            .setContentText(contextText)
            .setContentIntent(pendingNotificationIntent)
            .setStyle(new NotificationCompat.BigTextStyle().bigText(contextText))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setVibrate(new long[] { 1000, 1000, 1000, 1000, 1000 })
            .setLights(Color.GREEN, 3000, 3000)
            .setAutoCancel(true);
    notificationManager.notify(0, builder.build());

  }

}

