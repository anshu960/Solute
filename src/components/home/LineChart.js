import React from "react";
import { Chart } from "react-google-charts";

const data = [
  ["Year", "Sales"],
  ["2004", 1000],
  ["2005", 1170],
  ["2006", 660],
  ["2007", 1030],
];

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
    baselineColor: '#fff',
    textPosition: 'none',
    gridlines: {
        color: 'transparent'
    }
  }
};

export default function LineChart() {
  return (
    <Chart
      chartType="LineChart"
      width="50"
      height="50"
      data={data}
      options={options}
    />
  );
}