import React from "react";
import { Chart } from "react-google-charts";

const data = [
  ["Year", "Sales"],
  ["2004", 800],
  ["2005", 1270],
  ["2006", 860],
  ["2007", 120],
  ["2008", 1500],
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
    baselineColor: 'none',
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
      width="80"
      height="80"
      data={data}
      options={options}
    />
  );
}