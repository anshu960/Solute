import React from 'react';
// @mui
import { Grid, Stack, Typography } from '@mui/material';
//
import FullCalendar from "@fullcalendar/react";
import dayGridPlugin from "@fullcalendar/daygrid";
import { CalendarStyle } from './calender';

// ----------------------------------------------------------------------

export default function ProfileLeaves() {
  function getDate(dayString) {
    const today = new Date();
    const year = today.getFullYear().toString();
    let month = (today.getMonth() + 1).toString();
  
    if (month.length === 1) {
      month = "0" + month;
    }
  
    return dayString.replace("YEAR", year).replace("MONTH", month);
  }
  const events = [{ title: "Sick Leave", start: getDate("YEAR-MONTH-10") }];
  return (
    <Grid container spacing={3}>
      <Grid item xs={12} md={4}>
        <Stack spacing={3}>
          <Typography variant='h6'>
            Sick Leave : 1
          </Typography>
        </Stack>
      </Grid>

      <Grid item xs={12} md={8}>
        <Stack spacing={3}>
          <CalendarStyle>
            <FullCalendar
            defaultView="dayGridMonth"
            plugins={[dayGridPlugin]}
            events={events}
          />
        </CalendarStyle >  
        </Stack>
      </Grid>
    </Grid>
  );
}
