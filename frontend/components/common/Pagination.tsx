import Button from "@components/button/Button";
import usePaginationStore from "store/usePaginationStore";

interface PaginationProps {
  pageId: string;
}

export default function Pagination({ pageId }: PaginationProps) {
  const currentPage = usePaginationStore(state => state.currentPage[pageId] || 1);
  const setCurrentPage = usePaginationStore(state => state.setCurrentPage);

  const totalPage = 10;
  // 얘는 (items / 화면에 보여지는 아이템 갯수)로 할듯

  const PageList = [];
  const range = 1;

  const handlePage = (newPage: number) => {
    if (newPage > 0 && newPage <= totalPage && newPage !== currentPage) {
      setCurrentPage(pageId, newPage);
    }
  };

  for (let i = 1; i <= totalPage; i++) {
    if (i === 1 || i === totalPage || (i >= currentPage - range && i <= currentPage + range)) {
      PageList.push(i);
    } else if (PageList[PageList.length - 1] !== "...") {
      PageList.push("...");
    }
  }

  return (
    <nav className="flex w-[450px] items-center justify-between gap-4">
      <ul className="flex gap-2 justify-between w-[360px]">
        <Button size="s" color="primary" isLoading={currentPage === 1} onClick={() => handlePage(currentPage - 1)}>
          &laquo;
        </Button>
        <div className="flex gap-2">
          {PageList.map((page, index) => (
            <li key={index} className={`text-lg font-black ${page === currentPage ? "text-orange-800" : ""}`}>
              {typeof page === "number" ? (
                <Button size="s" color="primary" onClick={() => handlePage(page)}>
                  {page}
                </Button>
              ) : (
                <span className="flex px-4 py-2 text-center">{page}</span>
              )}
            </li>
          ))}
        </div>
        <Button
          size="s"
          color="primary"
          isLoading={currentPage === totalPage}
          onClick={() => handlePage(currentPage + 1)}>
          &raquo;
        </Button>
      </ul>
    </nav>
  );
}
