import React, { Fragment } from 'react';
import Slider from 'react-slick';
import PropTypes from 'prop-types';
import { motion } from 'framer-motion';
import { useState, useRef } from 'react';
import { Link as RouterLink } from 'react-router-dom';
// material
import { alpha, useTheme, styled } from '@mui/material/styles';
import { CardContent, Box, Typography, Card } from '@mui/material';
// utils
//import mockData from '../../../utils/mock-data';
//
import { MotionContainer, varFadeInRight } from '../animate';
import { CarouselControlsPaging1, CarouselControlsArrowsBasic1 } from '../carousel';
// ----------------------------------------------------------------------

const TITLES = ['Grow your business exponential', 'Manage your business anywhere and everywhere'];
const IMAGES = ['https://firebasestorage.googleapis.com/v0/b/fuelme-20ef9.appspot.com/o/fuel_me_website_content%2Fhome_curosel%2Fbusinessman.jpg?alt=media&token=10fa96b2-51ac-4fb0-9e3e-a2ee8e2c71c2',
'https://firebasestorage.googleapis.com/v0/b/fuelme-20ef9.appspot.com/o/fuel_me_website_content%2Fhome_curosel%2Fbusinessman_sale.jpg?alt=media&token=579e03be-db40-472f-95fc-7e3d83f24295'];

const MOCK_APPS = [...Array(2)].map((_, index) => ({
  id: index,
  title: TITLES[index],
  description: '',
  image: IMAGES[index]
}));
const CarouselImgStyle = styled('img')(({ theme }) => ({
  height: "60%",
  width: '100%',
  objectFit: 'cover',
  [theme.breakpoints.up('xl')]: {
    height: "60%"
  }
}));

// ----------------------------------------------------------------------

CarouselItem.propTypes = {
  item: PropTypes.object,
  isActive: PropTypes.bool
};

function CarouselItem({ item, isActive }) {
  const { image, title, description } = item;

  return (
    <RouterLink to="#">
      <Box sx={{ position: 'relative' }}>
        <Box
          sx={{
            top: 0,
            width: '100%',
            height: '100%',
            position: 'absolute',
            bgcolor: (theme) => alpha(theme.palette.grey[900], 0.72)
          }}
        />
        <CarouselImgStyle alt={title} src={image} />
        <CardContent
          sx={{
            bottom: 0,
            width: '100%',
            textAlign: 'left',
            position: 'absolute',
            color: 'common.white'
          }}
        >
          <MotionContainer open={isActive}>
            <motion.div variants={varFadeInRight}>
              <Typography
                variant="overline"
                sx={{
                  mb: 1,
                  opacity: 0.48,
                  display: 'block'
                }}
              >
                Featured 
              </Typography>
            </motion.div>
            <motion.div variants={varFadeInRight}>
              <Typography variant="h5" gutterBottom noWrap>
                {title}
              </Typography>
            </motion.div>
            <motion.div variants={varFadeInRight}>
              <Typography variant="body2" noWrap>
                {description}
              </Typography>
            </motion.div>
          </MotionContainer>
        </CardContent>
      </Box>
    </RouterLink>
  );
}

export default function AppFeatured() {
  const theme = useTheme();
  const carouselRef = useRef();
  const [currentIndex, setCurrentIndex] = useState(theme.direction === 'rtl' ? MOCK_APPS.length - 1 : 0);

  const settings = {
    speed: 800,
    dots: true,
    arrows: false,
    autoplay: true,
    slidesToShow: 1,
    slidesToScroll: 1,
    rtl: Boolean(theme.direction === 'rtl'),
    beforeChange: (current, next) => setCurrentIndex(next),
    ...CarouselControlsPaging1({
      color: 'primary.main',
      sx: {
        top: theme.spacing(3),
        left: theme.spacing(3),
        bottom: 'auto',
        right: 'auto',
      }
    })
  };

  const handlePrevious = () => {
    carouselRef.current.slickPrev();
  };

  const handleNext = () => {
    carouselRef.current.slickNext();
  };

  return (
    <Card sx={{borderRadius: 0, '& .slick-slide': {height:'auto !important'}}}>
      <Slider ref={carouselRef} {...settings}>
        {MOCK_APPS.map((app, index) => (
          <CarouselItem key={app.id} item={app} isActive={index === currentIndex} />
        ))}
      </Slider>
      <CarouselControlsArrowsBasic1 onNext={handleNext} onPrevious={handlePrevious} />
    </Card>
  );
}
