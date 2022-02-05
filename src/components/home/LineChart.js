import React from "react";
import { Chart } from "react-google-charts";

const options = {
  //title: "Company Performance",
  curveType: "function",
  legend: { position: "none" },
  backgroundColor: { fill:'transparent' },
  hAxis: {
    textPosition: 'none',
    gridlines: {
      color: 'transparent'
    }
  },
  vAxis: {
    baselineColor: 'none',
    textPosition: 'none',
    gridlines: {
        color: 'transparent'
    }
  }
};

export default function LineChart({sale}) {
  const data = [
    ["instance", "Sales"],
  ];
  data.push(['1',sale/4]);
  data.push(['2',sale*3]);
  data.push(['3',sale/2]);
  data.push(['4',sale]);
  return (
    <Chart
      chartType="LineChart"
      width="80"
      height="80"
      data={data}
      options={options}
    />
  );
}