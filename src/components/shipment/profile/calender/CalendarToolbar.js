import React from 'react'
// @mui
import { styled } from '@mui/material/styles';
import { Box, Stack, Button, Tooltip, Typography, IconButton, ToggleButton } from '@mui/material';

// ----------------------------------------------------------------------

const VIEW_OPTIONS = [
  { value: 'dayGridMonth', label: 'Month', icon: 'ic:round-view-module' },
  { value: 'timeGridWeek', label: 'Week', icon: 'ic:round-view-week' },
  { value: 'timeGridDay', label: 'Day', icon: 'ic:round-view-day' },
  { value: 'listWeek', label: 'Agenda', icon: 'ic:round-view-agenda' },
];

const RootStyle = styled('div')(({ theme }) => ({
  display: 'flex',
  alignItems: 'center',
  flexDirection: 'column',
  padding: theme.spacing(3, 0),
  [theme.breakpoints.up('sm')]: {
    flexDirection: 'row',
    padding: theme.spacing(1.75, 3),
    justifyContent: 'space-between',
  },
}));

// ----------------------------------------------------------------------

export default function CalendarToolbar({
  date,
  view,
  onNextDate,
  onPrevDate,
  onToday,
  onChangeView,
}) {

  return (
    <RootStyle>
        <Stack direction="row" spacing={1}>
          {VIEW_OPTIONS.map((viewOption) => (
            <Tooltip key={viewOption.value} title={viewOption.label}>
              <ToggleButton
                value={view}
                selected={viewOption.value === view}
                onChange={() => onChangeView(viewOption.value)}
                sx={{ width: 32, height: 32, padding: 0 }}
              >
                {/* <Iconify icon={viewOption.icon} width={20} height={20} /> */}
              </ToggleButton>
            </Tooltip>
          ))}
        </Stack>
      <Typography variant="h5" sx={{ my: { xs: 1, sm: 0 } }}>
        {date}
      </Typography>

      <Box sx={{ display: 'flex', alignItems: 'center' }}>
        <IconButton onClick={onPrevDate}>
          {/* <Iconify icon="eva:arrow-ios-back-fill" width={18} height={18} /> */}
        </IconButton>

        <Button size="small" color="error" variant="contained" onClick={onToday} sx={{ mx: 0.5 }}>
          Today
        </Button>

        <IconButton onClick={onNextDate}>
          {/* <Iconify icon="eva:arrow-ios-forward-fill" width={18} height={18} /> */}
        </IconButton>
      </Box>
    </RootStyle>
  );
}
