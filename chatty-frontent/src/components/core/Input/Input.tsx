import React from 'react';
import classes from './Input.module.scss';

interface InputProps extends React.InputHTMLAttributes<HTMLInputElement> {
  type: 'text' | 'textarea';
  variant: 'small' | 'medium' | 'large';
  placeholder?: string;
  value?: string;
}

const Input: React.FC<InputProps> = ({ type, onChange, placeholder, value, onKeyUp, variant }) => {
  return (
    <input
      className={`${classes['c-input']} ${classes[`c-input--${variant}`]}`}
      type={type}
      onChange={onChange}
      placeholder={placeholder}
      value={value}
      onKeyUp={onKeyUp}
    />
  );
};

export default Input;
