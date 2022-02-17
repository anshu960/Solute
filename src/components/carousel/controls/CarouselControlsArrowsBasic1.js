import React from 'react';
import PropTypes from 'prop-types';

// material
import { useTheme, styled } from '@mui/material/styles';
import { Box, Button } from '@mui/material';
import ArrowLeftIcon from '@mui/icons-material/ArrowLeft';
import ArrowRightIcon from '@mui/icons-material/ArrowRight';

// ----------------------------------------------------------------------

const ICON_SIZE = {
  width: 20,
  height: 20
};

const RootStyle = styled(Box)(({ theme }) => ({
  zIndex: 9,
  display: 'flex',
  position: 'absolute',
  top: theme.spacing(2),
  right: theme.spacing(2)
}));

const ArrowStyle = styled(Button)(({ theme }) => ({
  padding: 6,
  opacity: 0.48,
  color: theme.palette.common.white,
  '&:hover': { opacity: 1 }
}));

// ----------------------------------------------------------------------

CarouselControlsArrowsBasic1.propTypes = {
  arrowLine: PropTypes.bool,
  onNext: PropTypes.func,
  onPrevious: PropTypes.func
};

export default function CarouselControlsArrowsBasic1({ arrowLine, onNext, onPrevious, ...other }) {
  const theme = useTheme();
  const isRTL = theme.direction === 'rtl';

  return (
    <RootStyle {...other}>
      <ArrowStyle size="small" onClick={onPrevious}>
        {arrowLine ? (
          isRTL ? <ArrowRightIcon /> : <ArrowLeftIcon {...ICON_SIZE} />
        ) : (
          isRTL ? <ArrowRightIcon /> : <ArrowLeftIcon {...ICON_SIZE} />
        )}
      </ArrowStyle>

      <ArrowStyle size="small" onClick={onNext}>
        {arrowLine ? (
          isRTL ? <ArrowLeftIcon /> : <ArrowRightIcon {...ICON_SIZE} />
        ) : (
            isRTL ? <ArrowLeftIcon /> : < ArrowRightIcon {...ICON_SIZE} />
        )}
      </ArrowStyle>
    </RootStyle>
  );
}
