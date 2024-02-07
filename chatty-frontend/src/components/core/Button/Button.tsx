import React from 'react';
import classes from './Button.module.scss';

interface ButtonProps {
  variant: 'primary' | 'secondary';
  size: 'small' | 'medium' | 'large' | 'icon';
  label: string | JSX.Element;
  onClick?: () => void;
}

const Button: React.FC<ButtonProps> = ({ label, variant, onClick, size }) => {
  return (
    <button
      className={`${classes['c-button']} ${classes[`c-button--${variant}`]} ${
        classes[`c-button--${size}`]
      }`}
      onClick={onClick}
    >
      {label}
    </button>
  );
};

export default Button;
