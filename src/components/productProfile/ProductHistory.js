import React, { useState } from 'react';
// @mui
import { alpha,styled, useTheme } from '@mui/material/styles';
import { Typography} from '@mui/material';
import { Timeline, TimelineConnector, TimelineContent, TimelineDot, TimelineItem, TimelineOppositeContent, TimelineSeparator } from '@mui/lab';
// ----------------------------------------------------------------------

const RootStyle = styled('div')(({ theme }) => ({
  '&:before': {
    top: 0,
    zIndex: 9,
    content: "''",
    width: '100%',
    height: '100%',
    position: 'absolute',
  },
}));

// ----------------------------------------------------------------------
const payments = [
    {
        PaymentMode: 'Cash',
        PaymentReference: '50,000',
        PaymentDate: new Date()
    }
]
export default function ProductHistory() {
  return (
    <RootStyle>
      <Timeline>
            {payments.length ? (
            <TimelineItem>
            <TimelineOppositeContent color="text.secondary">
                <Typography variant="subtitle2">{payments[0].PaymentMode}</Typography>
                <Typography variant="caption" sx={{ color: 'text.secondary' }}>{payments[0].PaymentReference}</Typography>
            </TimelineOppositeContent>
            <TimelineSeparator>
                <TimelineDot/>
                <TimelineConnector />
            </TimelineSeparator>
            <TimelineContent>
                <Typography variant="subtitle2">{payments[0].Amount}</Typography>
                <Typography variant="caption" sx={{ color: 'text.secondary' }}>
                {new Date(payments[0].PaymentDate).toLocaleDateString()}
                </Typography>
            </TimelineContent>
            </TimelineItem>
            ) : null}
        {payments.length>=2 ? (
            <TimelineItem>
            <TimelineOppositeContent color="text.secondary">
            <Typography variant="subtitle2">{payments[1].PaymentReference}</Typography>
            </TimelineOppositeContent>
            <TimelineSeparator>
            <TimelineDot/>
            <TimelineConnector />
            </TimelineSeparator>
            <TimelineContent>
            <Typography variant="subtitle2">{payments[1].Amount}</Typography>
            <Typography variant="caption" sx={{ color: 'text.secondary' }}>
            {new Date(payments[1].PaymentDate).toLocaleDateString()}
            </Typography>
            </TimelineContent>
        </TimelineItem>
        ):null}
        {payments.length>=3 ? (
            <TimelineItem>
            <TimelineOppositeContent color="text.secondary">
            <Typography variant="subtitle2">{payments[2].PaymentReference}</Typography>
            </TimelineOppositeContent>
            <TimelineSeparator>
            <TimelineDot/>
            </TimelineSeparator>
            <TimelineContent>
            <Typography variant="subtitle2">{payments[2].Amount}</Typography>
            <Typography variant="caption" sx={{ color: 'text.secondary' }}>
            {new Date(payments[2].PaymentDate).toLocaleDateString()}
            </Typography>
            </TimelineContent>
        </TimelineItem>
        ):null}
        </Timeline>
    </RootStyle>
  );
}
