package us.kripet.bartleby;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Bartleby extends Activity {
  private ListView stationList;

  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.main);

      loadStationList();

  }
  private void loadStationList() {
    if (stationList == null) stationList = (ListView)findViewById(R.id.stationList);
    String[] stations = Station.allNames();
    stationList.setAdapter(new ArrayAdapter<String>(this, R.layout.station_item, stations));
  }
}
