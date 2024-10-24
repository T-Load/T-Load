import { buttonColor, buttonColorType, buttonSize, buttonSizeType } from "types/button";

interface ButtonProps {
  size: buttonSizeType;
  color: buttonColorType;
  isLoading?: boolean;
  children: React.ReactNode;
}

const Button = ({ size, color, isLoading, children, ...rest }: ButtonProps) => {
  const sizeClass = buttonSize[size];
  const colorClass = buttonColor[color];

  return (
    <>
      <button className={`flex-center ${sizeClass} ${colorClass}`} disabled={isLoading} {...rest}>
        {" "}
        {isLoading ? "Loading.." : children}
      </button>
    </>
  );
};

export default Button;
